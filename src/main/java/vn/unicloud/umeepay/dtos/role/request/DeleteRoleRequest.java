package vn.unicloud.umeepay.dtos.role.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRoleRequest extends BaseRequestData {

    private Long id;

}
