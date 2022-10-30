package vn.unicloud.umeepay.dtos.admin.request;

import lombok.*;
import vn.unicloud.umeepay.core.BaseRequestData;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetAdminDetailRequest extends BaseRequestData {
    private String id;
}
