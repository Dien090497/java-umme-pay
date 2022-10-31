package vn.unicloud.umeepay.handler.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.role.request.GetAllPermissionRequest;
import vn.unicloud.umeepay.dtos.role.response.GetAllPermissionResponse;
import vn.unicloud.umeepay.dtos.role.response.PermissionResponse;
import vn.unicloud.umeepay.entity.Permission;
import vn.unicloud.umeepay.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllPermissionHandler extends RequestHandler<GetAllPermissionRequest, GetAllPermissionResponse> {

    private final RoleService roleService;

    @Override
    public GetAllPermissionResponse handle(GetAllPermissionRequest request) {
        List<Permission> permissions = roleService.getAllPermissions(request.getScope());
        return new GetAllPermissionResponse(
                permissions
                        .stream()
                        .map(per -> new PermissionResponse(per))
                        .collect(Collectors.toList())
        );
    }
}
