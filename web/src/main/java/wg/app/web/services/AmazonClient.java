package wg.app.web.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wg.app.model.amazon.FileStorage;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AmazonClient {

    private AmazonS3 amazonS3Client;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @Value("${amazonProperties.folderName}")
    private String folderName;

    @PostConstruct
    public void initializeAmazon() {

        AWSCredentials credentials
                = new BasicAWSCredentials(accessKey, secretKey);

        amazonS3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        log.info("BUCKETS: {}", getAllBuckets());
        log.info("FILES: {}", getAllFiles());

    }

    public String uploadFile( FileStorage fileStorage ) {

        if ( fileStorage == null ) {
            throw new IllegalArgumentException("upload file - file storage object is null");
        }

        try {

            final String filename = generateFilename(fileStorage.getFile());
            final File file = convertMultipartFileToFile(fileStorage.getFile());
            amazonS3Client.putObject(bucketName, folderName + "/" + filename, file);
            deleteTempFile(file);
            return filename;

        } catch ( Exception e ) {

            throw new IllegalStateException("upload file exception");

        }

    }

    public String downloadFile( String filename ) {

        if ( filename == null ) {
            throw new IllegalArgumentException("filename is null");
        }

        try {

            S3Object s3Object = amazonS3Client.getObject( bucketName, folderName + "/" + filename );
            FileUtils.copyInputStreamToFile(s3Object.getObjectContent(), new File(s3Object.getKey()));
            return s3Object.getKey();
        } catch ( Exception e ) {
            throw new IllegalStateException("download file exception");
        }

    }

    public String deleteFile( String filename ) {

        if ( filename == null ) {
            throw new IllegalArgumentException("filename is null");
        }

        try {

            amazonS3Client.deleteObject( bucketName, folderName + "/" + filename );
            return filename;

        } catch ( Exception e ) {
            throw new IllegalStateException("delete file exception");
        }

    }



    private static File convertMultipartFileToFile( MultipartFile file ) {
        try {

            File copy = new File(file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(copy);
            fos.write(file.getBytes());
            fos.close();
            return copy;
        } catch ( Exception e ) {
            throw new IllegalStateException("convert multipart file to file exception");
        }
    }

    private static boolean deleteTempFile( File file ) {
        return file.delete();
    }

    private String getAllBuckets() {
        return amazonS3Client
                .listBuckets()
                .stream()
                .map(Bucket::getName)
                .collect(Collectors.joining(","));
    }

    private String getAllFiles() {
        return amazonS3Client
                .listObjects(bucketName)
                .getObjectSummaries()
                .stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.joining(","));
    }


    private static String generateFilename(MultipartFile file)
    {
        String extension = file.getOriginalFilename().split("\\.")[1];
        String filenamePart1 = UUID.randomUUID().toString().replace("\\W", "");
        String filenamePart2 = String.valueOf(System.currentTimeMillis());
        System.err.println(filenamePart1);
        return filenamePart1 + "-" + filenamePart2 + "." + extension;
    }

}
