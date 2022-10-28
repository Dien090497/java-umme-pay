package vn.unicloud.umeepay.handler.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.admin.request.BlockAdminRequest;
import vn.unicloud.umeepay.dtos.admin.response.AdminResponse;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.enums.UserStatus;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.AdminService;
import vn.unicloud.umeepay.service.ContextService;
import vn.unicloud.umeepay.service.RedisService;
import vn.unicloud.umeepay.utils.RedisKeyUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class BlockAdminHandler extends RequestHandler<BlockAdminRequest, AdminResponse> {

    private final AdminService adminService;

    private final ContextService contextService;

    private final RedisService redisService;

    @Override
    @Transactional
    public AdminResponse handle(BlockAdminRequest request) {
        Administrator admin = adminService.getById(request.getId());
        if (admin == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }

        if (UserStatus.INACTIVE.equals(admin.getStatus())) {
            throw new InternalException(ResponseCode.INVALID_USER_STATUS);
        }

        admin.setStatus(UserStatus.INACTIVE);
        admin.setBlockedAt(LocalDateTime.now());
        admin.setBlockedBy(contextService.getLoggedInUsername().orElse(null));

        if (adminService.saveAdmin(admin) != null) {
            // Update redis
            redisService.setValue(RedisKeyUtils.getUserStatusKey(admin.getId()), UserStatus.INACTIVE);
            return new AdminResponse(admin);
        }

        throw new InternalException(ResponseCode.FAILED);
    }
}
