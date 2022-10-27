package vn.unicloud.umeepay.dtos.admin.request;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseRequestData;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class BlockAdminRequest extends BaseRequestData {

    @NotNull
    private String id;

}
