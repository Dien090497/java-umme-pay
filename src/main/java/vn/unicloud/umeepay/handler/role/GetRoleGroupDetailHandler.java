package vn.unicloud.umeepay.handler.role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.role.request.GetRoleGroupDetailRequest;
import vn.unicloud.umeepay.dtos.role.response.RoleGroupDetailResponse;
import vn.unicloud.umeepay.entity.RoleGroup;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.RoleService;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetRoleGroupDetailHandler extends RequestHandler<GetRoleGroupDetailRequest, RoleGroupDetailResponse> {

    private final RoleService roleService;

    @Override
    public RoleGroupDetailResponse handle(GetRoleGroupDetailRequest request) {
        RoleGroup role = roleService.getRoleById(request.getId());
        if (role == null) {
            throw new InternalException(ResponseCode.ROLE_ERROR_NOT_FOUND);
        }

       try {
           RoleGroupDetailResponse response = new RoleGroupDetailResponse(role);
           boolean isCurrentUsed = !role.getUsers().isEmpty() || !role.getAdmins().isEmpty();
           response.setCurrentUsed(isCurrentUsed);
           return response;
       } catch (Exception ex) {
           log.error("Get role group detailed failed {}", ex.getMessage());
       }

       throw new InternalException(ResponseCode.FAILED);
    }
}
