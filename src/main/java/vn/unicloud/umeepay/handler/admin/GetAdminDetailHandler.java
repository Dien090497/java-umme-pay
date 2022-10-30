package vn.unicloud.umeepay.handler.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.admin.request.GetAdminDetailRequest;
import vn.unicloud.umeepay.dtos.admin.response.AdminDetailResponse;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.AdminService;

@Component
@RequiredArgsConstructor
public class GetAdminDetailHandler extends RequestHandler<GetAdminDetailRequest, AdminDetailResponse> {

    private final AdminService adminService;

    @Override
    public AdminDetailResponse handle(GetAdminDetailRequest request) {
        Administrator admin = adminService.getById(request.getId());
        if (admin != null) {
            return new AdminDetailResponse(admin);
        }
        throw new InternalException(ResponseCode.USER_NOT_FOUND);
    }
}
