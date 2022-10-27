package vn.unicloud.umeepay.dtos.admin.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAdminRequest extends BaseRequestData {

    @NotEmpty
    private String id;

}
