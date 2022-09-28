package vn.unicloud.umeepay.dtos.merchant.request;

import lombok.Data;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class UpdateWebhookRequest extends BaseRequestData {

    @NotBlank
    private String url;

    @NotBlank
    private String apiKey;

}
