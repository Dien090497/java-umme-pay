package vn.unicloud.umeepay.handler.role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.dtos.role.request.GetListRoleRequest;
import vn.unicloud.umeepay.dtos.role.response.RoleResponse;
import vn.unicloud.umeepay.entity.Role;
import vn.unicloud.umeepay.service.RoleService;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetListRoleHandler extends RequestHandler<GetListRoleRequest, PageResponse<RoleResponse>> {

    private final RoleService roleService;

    @Override
    public PageResponse<RoleResponse> handle(GetListRoleRequest request) {
        Page<Role> result = roleService.getListRole(request, request.getPageable());
        return new PageResponse<RoleResponse>()
                .setPageSize(result.getSize())
                .setPageNumber(result.getNumber())
                .setTotalSize(result.getTotalElements())
                .setItems(result.getContent()
                        .stream()
                        .map(role -> new RoleResponse(role))
                        .collect(Collectors.toList()));
    }
}
