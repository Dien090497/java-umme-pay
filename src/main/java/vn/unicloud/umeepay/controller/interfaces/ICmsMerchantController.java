package vn.unicloud.umeepay.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.unicloud.umeepay.config.OpenApiConfig;
import vn.unicloud.umeepay.constant.BaseConstant;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.merchant.request.CmsDisapproveMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.response.MerchantDetailResponse;
import vn.unicloud.umeepay.dtos.merchant.response.MerchantResponse;
import vn.unicloud.umeepay.dtos.user.response.UserResponse;
import vn.unicloud.umeepay.enums.BusinessType;
import vn.unicloud.umeepay.enums.MerchantStatus;
import vn.unicloud.umeepay.enums.UserStatus;

import javax.validation.Valid;
import java.util.Date;

@Tag(name = "[CMS] Merchant Controller", description = "[CMS] Thao tác với merchant")
@RequestMapping(value = "/api/merchant/cms")
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public interface ICmsMerchantController {

    @Operation(
            summary = "Xem danh sách tất cả cả merchant",
            description = "- Xem danh sách tất cả cả merchant"
    )
    @GetMapping(value = "/v1/getAll")
    ResponseEntity<ResponseBase<PageResponse<MerchantResponse>>> getAllMerchant(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(pattern = BaseConstant.DEFAULT_DATE_FORMAT) Date requestDateFrom,
            @RequestParam(required = false) @DateTimeFormat(pattern = BaseConstant.DEFAULT_DATE_FORMAT) Date requestDateTo,
            @RequestParam(required = false) BusinessType businessType,
            @RequestParam(required = false) MerchantStatus status,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_NUMBER_STRING) Integer page,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SIZE_STRING) Integer pageSize,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SORT_DIRECTION) Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SORT_BY) String sortBy
    );

    @Operation(
            summary = "Xem chi tiết đối tác",
            description = "- Xem chi tiết đối tác"
    )
    @GetMapping(value = "/v1/getDetail/{id}")
    ResponseEntity<ResponseBase<MerchantDetailResponse>> getMerchantDetail(@PathVariable String id);

    @Operation(
            summary = "Duyệt đối tác",
            description = "- Duyệt đối tác"
    )
    @PostMapping(value = "/v1/approve/{id}")
    ResponseEntity<ResponseBase<MerchantResponse>> approveMerchant(@PathVariable String id);

    @Operation(
            summary = "Từ chối duyệt đối tác",
            description = "- Từ chối duyệt đối tác"
    )
    @PostMapping(value = "/v1/disapprove/{id}")
    ResponseEntity<ResponseBase<MerchantResponse>> disapproveMerchant(@PathVariable String id, @RequestBody @Valid CmsDisapproveMerchantRequest request);

    @Operation(
            summary = "Lấy danh sách thành viên của đối tác",
            description = "- Lấy danh sách thành viên đối tác"
    )
    @GetMapping(value = "/v1/getMembers/{merchantId}")
    ResponseEntity<ResponseBase<PageResponse<UserResponse>>> getAllMembers(
            @PathVariable String merchantId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) UserStatus status,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_NUMBER_STRING) Integer page,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SIZE_STRING) Integer pageSize,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SORT_DIRECTION) Sort.Direction sortDirection,
            @RequestParam(required = false, defaultValue = BaseConstant.DEFAULT_PAGE_SORT_BY) String sortBy
    );

    @Operation(
            summary = "Vô hiệu hóa thành viên đối tác",
            description = "- Vô hiệu hóa thành viên đối tác"
    )
    @PutMapping(value = "/v1/blockMember/{memberId}")
    ResponseEntity<ResponseBase<StatusResponse>> blockMerchantMember(@PathVariable String memberId);

    @Operation(
            summary = "Kích hoạt thành viên đối tác",
            description = "- Vô hiệu hóa thành viên đối tác"
    )
    @PutMapping(value = "/v1/unblockMember/{memberId}")
    ResponseEntity<ResponseBase<StatusResponse>> unblockMerchantMember(@PathVariable String memberId);


}
