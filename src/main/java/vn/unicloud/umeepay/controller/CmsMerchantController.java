package vn.unicloud.umeepay.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.ICmsMerchantController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.dtos.common.StatusResponse;
import vn.unicloud.umeepay.dtos.merchant.request.*;
import vn.unicloud.umeepay.dtos.merchant.response.MerchantDetailResponse;
import vn.unicloud.umeepay.dtos.merchant.response.MerchantResponse;
import vn.unicloud.umeepay.dtos.user.response.UserResponse;
import vn.unicloud.umeepay.enums.BusinessType;
import vn.unicloud.umeepay.enums.MerchantStatus;
import vn.unicloud.umeepay.enums.UserStatus;

import java.util.Date;

@RestController
public class CmsMerchantController extends BaseController implements ICmsMerchantController {

    @Override
    public ResponseEntity<ResponseBase<PageResponse<MerchantResponse>>> getAllMerchant(String username,
                                                                                       String name,
                                                                                       Date requestDateFrom,
                                                                                       Date requestDateTo,
                                                                                       BusinessType businessType,
                                                                                       MerchantStatus status,
                                                                                       Integer page,
                                                                                       Integer pageSize,
                                                                                       Sort.Direction sortDirection,
                                                                                       String sortBy) {


        CmsGetAllMerchantRequest request = new CmsGetAllMerchantRequest()
                .setName(name)
                .setUsername(username)
                .setRequestDateFrom(requestDateFrom)
                .setRequestDateTo(requestDateTo)
                .setBusinessType(businessType)
                .setStatus(status)
                .setPageable(PageRequest.of(page, pageSize, Sort.by(sortDirection, sortBy)));

        return this.execute(request, (Class) PageResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<MerchantDetailResponse>> getMerchantDetail(String id) {
        return this.execute(new CmsGetMerchantRequest(id), MerchantDetailResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<MerchantResponse>> approveMerchant(String id) {
        return this.execute(new CmsApproveMerchantRequest(id), MerchantResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<MerchantResponse>> disapproveMerchant(String id, CmsDisapproveMerchantRequest request) {
        request.setId(id);
        return this.execute(request, MerchantResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<PageResponse<UserResponse>>> getAllMembers(String merchantId,
                                                                                  String username,
                                                                                  UserStatus status,
                                                                                  Integer page,
                                                                                  Integer pageSize,
                                                                                  Sort.Direction sortDirection,
                                                                                  String sortBy) {
        CmsGetMerchantMemberRequest request = new CmsGetMerchantMemberRequest()
                .setMerchantId(merchantId)
                .setUsername(username)
                .setStatus(status)
                .setPageable(PageRequest.of(page, pageSize, Sort.by(sortDirection, sortBy)));

        return this.execute(request, (Class) PageResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> blockMerchantMember(String memberId) {
        CmsBlockMerchantMemberRequest request = new CmsBlockMerchantMemberRequest(memberId);
        return this.execute(request, StatusResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> unblockMerchantMember(String memberId) {
        CmsUnblockMerchantMemberRequest request = new CmsUnblockMerchantMemberRequest(memberId);
        return this.execute(request, StatusResponse.class);
    }


}
