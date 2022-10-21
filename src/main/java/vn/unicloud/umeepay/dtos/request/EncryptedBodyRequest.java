package vn.unicloud.umeepay.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.model.CustomerInfo;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class EncryptedBodyRequest extends BaseRequestData {

    @NotBlank
    private String data;

}
