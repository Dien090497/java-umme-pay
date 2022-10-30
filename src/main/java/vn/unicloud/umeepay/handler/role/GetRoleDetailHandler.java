package vn.unicloud.umeepay.handler.role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.role.request.GetRoleDetailRequest;
import vn.unicloud.umeepay.dtos.role.response.RoleDetailResponse;
import vn.unicloud.umeepay.entity.Role;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.RoleService;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetRoleDetailHandler extends RequestHandler<GetRoleDetailRequest, RoleDetailResponse> {

    private final RoleService roleService;

    @Override
    public RoleDetailResponse handle(GetRoleDetailRequest request) {
        Role role = roleService.getRoleById(request.getId());
        if (role == null) {
            throw new InternalException(ResponseCode.ROLE_ERROR_NOT_FOUND);
        }

        return new RoleDetailResponse(role);
    }
}
