package vn.unicloud.umeepay.handler.merchant;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.dtos.merchant.request.CmsGetMerchantMemberRequest;
import vn.unicloud.umeepay.dtos.user.response.UserResponse;
import vn.unicloud.umeepay.entity.User;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.service.MerchantService;
import vn.unicloud.umeepay.service.UserService;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CmsGetMerchantMemberHandler extends RequestHandler<CmsGetMerchantMemberRequest, PageResponse<UserResponse>> {

    private final UserService userService;

    private final MerchantService merchantService;

    @Override
    public PageResponse<UserResponse> handle(CmsGetMerchantMemberRequest request) {

        if (merchantService.getMerchantById(request.getMerchantId()) == null) {
            throw new InternalException(ResponseCode.MERCHANT_NOT_FOUND);
        }

        Page<User> members = userService.getAllUsers(request, request.getPageable());
        return new PageResponse<UserResponse>()
                .setTotalSize(members.getTotalElements())
                .setTotalPage(members.getTotalPages())
                .setPageSize(members.getSize())
                .setPageNumber(members.getNumber())
                .setItems(
                        members.getContent()
                        .stream()
                        .map(mem -> new UserResponse(mem))
                        .collect(Collectors.toList())
                );
    }
}
