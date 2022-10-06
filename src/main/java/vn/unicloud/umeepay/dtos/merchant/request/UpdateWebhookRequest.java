package vn.unicloud.umeepay.dtos.merchant.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class UpdateWebhookRequest extends BaseRequestData {

    @NotBlank
    private String url;

    @NotBlank
    private String apiKey;

}
