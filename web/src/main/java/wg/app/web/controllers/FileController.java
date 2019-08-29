package wg.app.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wg.app.model.amazon.FileStorage;
import wg.app.model.amazon.Info;
import wg.app.web.services.AmazonClient;

import java.util.List;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final AmazonClient amazonClient;

    @PostMapping
    public Info uploadFile (@ModelAttribute FileStorage fileStorage ) {

        return Info
                .builder()
                .info(amazonClient.uploadFile(fileStorage))
                .build();
    }

    @GetMapping("/{filename}")
    public Info downloadFile ( @PathVariable String filename ) {

        return Info
                .builder()
                .info(amazonClient.downloadFile(filename))
                .build();
    }

    @DeleteMapping("/{filename}")
    public Info deleteFile ( @PathVariable String filename ) {

        return Info
                .builder()
                .info(amazonClient.deleteFile(filename))
                .build();
    }

}
