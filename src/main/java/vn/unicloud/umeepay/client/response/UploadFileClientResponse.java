package vn.unicloud.umeepay.client.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UploadFileClientResponse {

    private String bucket;

    private String path;

    private String name;

    private String extension;

    private String url;

    private String md5;

    private String contentType;


}
