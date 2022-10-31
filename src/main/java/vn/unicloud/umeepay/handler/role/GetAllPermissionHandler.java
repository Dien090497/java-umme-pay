package vn.unicloud.umeepay.handler.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.role.request.GetAllPermissionRequest;
import vn.unicloud.umeepay.dtos.role.response.GetAllPermissionResponse;
import vn.unicloud.umeepay.dtos.role.response.PermissionGroupResponse;
import vn.unicloud.umeepay.entity.PermissionGroup;
import vn.unicloud.umeepay.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllPermissionHandler extends RequestHandler<GetAllPermissionRequest, GetAllPermissionResponse> {

    private final RoleService roleService;

    @Override
    public GetAllPermissionResponse handle(GetAllPermissionRequest request) {
        List<PermissionGroup> permissions = roleService.getAllPermissions(request.getScope());
        return new GetAllPermissionResponse(
                permissions
                        .stream()
                        .map(per -> new PermissionGroupResponse(per))
                        .collect(Collectors.toList())
        );
    }
}
