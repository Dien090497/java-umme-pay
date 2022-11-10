package vn.unicloud.umeepay.handler.merchant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.unicloud.umeepay.core.RequestHandler;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.dtos.merchant.request.CmsGetAllMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.response.MerchantResponse;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.service.MerchantService;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class CmsGetAllMerchantHandler extends RequestHandler<CmsGetAllMerchantRequest, PageResponse<MerchantResponse>> {

    private final MerchantService merchantService;

    @Override
    public PageResponse<MerchantResponse> handle(CmsGetAllMerchantRequest request) {
        Page<Merchant> result = merchantService.getAllMerchant(request, request.getPageable());
        return new PageResponse<MerchantResponse>()
                .setPageNumber(result.getNumber())
                .setPageSize(result.getSize())
                .setTotalSize(result.getTotalElements())
                .setTotalPage(result.getTotalPages())
                .setItems(
                        result.getContent()
                                .stream()
                                .map(merchant -> new MerchantResponse(merchant))
                                .collect(Collectors.toList()));
    }
}
