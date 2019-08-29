package wg.app.model.amazon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import wg.app.model.user.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileStorage
{
    private User user;
    private MultipartFile file;
}

