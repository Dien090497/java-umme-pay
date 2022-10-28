package vn.unicloud.umeepay.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.umeepay.controller.interfaces.IAdminController;
import vn.unicloud.umeepay.core.BaseController;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.dtos.admin.request.*;
import vn.unicloud.umeepay.dtos.admin.response.AdminDetailResponse;
import vn.unicloud.umeepay.dtos.admin.response.AdminResponse;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.enums.OfficeType;
import vn.unicloud.umeepay.enums.UserStatus;

@RestController
public class AdminController extends BaseController implements IAdminController {

    @Override
    public ResponseEntity<ResponseBase<AdminResponse>> createAdmin(CreateAdminRequest request) {
        return this.execute(request, AdminResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<AdminResponse>> updateAdmin(String id, UpdateAdminRequest request) {
        request.setId(id);
        return this.execute(request, AdminResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<AdminDetailResponse>> getAdminDetail(String id) {
        GetAdminDetailRequest request = new GetAdminDetailRequest(id);
        return this.execute(request, AdminDetailResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<PageResponse<AdminResponse>>> getAll(String username,
                                                                            String fullName,
                                                                            String email,
                                                                            String staffId,
                                                                            OfficeType office,
                                                                            UserStatus status,
                                                                            Integer page,
                                                                            Integer pageSize,
                                                                            Sort.Direction sortDirection,
                                                                            String sortBy) {

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortDirection, sortBy));
        GetListAdminRequest request = new GetListAdminRequest()
                .setEmail(email)
                .setUsername(username)
                .setStatus(status)
                .setStaffId(staffId)
                .setOffice(office)
                .setFullName(fullName)
                .setPageable(pageable);

        return this.execute(request, (Class) PageResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<AdminResponse>> deleteAdmin(String id) {
        return this.execute(new DeleteAdminRequest(id), AdminResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<AdminResponse>> blockAdmin(String id) {
        return this.execute(new BlockAdminRequest(id), AdminResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<AdminResponse>> unblockAdmin(String id) {
        return this.execute(new UnblockAdminRequest(id), AdminResponse.class);
    }

}