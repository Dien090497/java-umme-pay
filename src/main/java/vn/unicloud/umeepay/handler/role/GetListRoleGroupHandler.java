package vn.unicloud.umeepay.handler.role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.dtos.role.request.GetListRoleGroupRequest;
import vn.unicloud.umeepay.dtos.role.response.RoleGroupResponse;
import vn.unicloud.umeepay.entity.RoleGroup;
import vn.unicloud.umeepay.service.RoleService;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetListRoleGroupHandler extends RequestHandler<GetListRoleGroupRequest, PageResponse<RoleGroupResponse>> {

    private final RoleService roleService;

    @Override
    public PageResponse<RoleGroupResponse> handle(GetListRoleGroupRequest request) {
        Page<RoleGroup> result = roleService.getListRole(request, request.getPageable());
        return new PageResponse<RoleGroupResponse>()
                .setPageSize(result.getSize())
                .setPageNumber(result.getNumber())
                .setTotalSize(result.getTotalElements())
                .setItems(result.getContent()
                        .stream()
                        .map(role -> new RoleGroupResponse(role))
                        .collect(Collectors.toList()));
    }
}
