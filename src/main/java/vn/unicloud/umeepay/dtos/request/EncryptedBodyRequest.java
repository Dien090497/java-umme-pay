package vn.unicloud.umeepay.dtos.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.model.CustomerInfo;

import javax.validation.constraints.*;

@Data
public class EncryptedBodyRequest extends BaseRequestData {

    @NotBlank
    private String data;

}
