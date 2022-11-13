package vn.unicloud.umeepay.client.request;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class UploadFileClientRequest {

    private String bucket;

    private String path;

    private String fileName;

    private MultipartFile file;

    private String mediaType;

}
