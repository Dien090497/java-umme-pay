package vn.unicloud.umeepay.handler.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.admin.request.GetListAdminRequest;
import vn.unicloud.umeepay.dtos.admin.response.AdminResponse;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.service.AdminService;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetListAdminHandler extends RequestHandler<GetListAdminRequest, PageResponse<AdminResponse>> {

    private final AdminService adminService;

    @Override
    public PageResponse<AdminResponse> handle(GetListAdminRequest request) {
        Page<Administrator> result = adminService.getAll(request, request.getPageable());
        return new PageResponse<AdminResponse>()
                .setPageNumber(result.getNumber())
                .setPageSize(result.getSize())
                .setTotalSize(result.getTotalElements())
                .setTotalPage(result.getTotalPages())
                .setItems(result.getContent()
                        .stream()
                        .map(admin -> new AdminResponse(admin))
                        .collect(Collectors.toList())
                );
    }
}
