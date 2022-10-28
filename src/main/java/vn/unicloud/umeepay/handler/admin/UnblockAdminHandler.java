package vn.unicloud.umeepay.handler.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.admin.request.BlockAdminRequest;
import vn.unicloud.umeepay.dtos.admin.request.UnblockAdminRequest;
import vn.unicloud.umeepay.dtos.admin.response.AdminResponse;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.UserStatus;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.AdminService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class UnblockAdminHandler extends RequestHandler<UnblockAdminRequest, AdminResponse> {

    private final AdminService adminService;

    private final RedisService redisService;

    @Override
    @Transactional
    public AdminResponse handle(UnblockAdminRequest request) {
        Administrator admin = adminService.getById(request.getId());
        if (admin == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }

        if (UserStatus.ACTIVE.equals(admin.getStatus())) {
            throw new InternalException(ResponseCode.INVALID_USER_STATUS);
        }

        admin.setStatus(UserStatus.ACTIVE);
        admin.setBlockedAt(null);
        admin.setBlockedBy(null);

        if (adminService.saveAdmin(admin) != null) {
            redisService.setValue(RedisKeyUtils.getUserStatusKey(admin.getId()), UserStatus.ACTIVE);
            return new AdminResponse(admin);
        }

        throw new InternalException(ResponseCode.FAILED);
    }
}
