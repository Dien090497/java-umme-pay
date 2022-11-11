package vn.unicloud.umeepay.dtos.ekyc.request;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.enums.EkycType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UploadCardRequest extends BaseRequestData implements Serializable {

    @NotNull
    private String merchantId;

    @NotNull
    private EkycType ekycType;

    @NotNull
    private MultipartFile imageFront;

    private MultipartFile imageBack;

}
