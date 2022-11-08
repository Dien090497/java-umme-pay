package vn.uniclound.umeepay.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import vn.unicloud.umeepay.dtos.common.PageResponse;
import vn.unicloud.umeepay.dtos.merchant.request.CmsApproveMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.request.CmsDisapproveMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.request.CmsGetAllMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.request.CmsGetMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.response.MerchantDetailResponse;
import vn.unicloud.umeepay.dtos.merchant.response.MerchantResponse;
import vn.unicloud.umeepay.entity.Administrator;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.enums.BusinessType;
import vn.unicloud.umeepay.enums.MerchantStatus;
import vn.unicloud.umeepay.enums.UserStatus;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.handler.merchant.CmsApproveMerchantHandler;
import vn.unicloud.umeepay.handler.merchant.CmsDisapproveMerchantHandler;
import vn.unicloud.umeepay.handler.merchant.CmsGetAllMerchantHandler;
import vn.unicloud.umeepay.handler.merchant.CmsGetMerchantHandler;
import vn.unicloud.umeepay.service.AdminService;
import vn.unicloud.umeepay.service.ContextService;
import vn.unicloud.umeepay.service.MerchantService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CmsMerchantManagementTest {

    @Mock
    private MerchantService merchantService;

    @Mock
    private ContextService contextService;

    @Mock
    private AdminService adminService;

    @InjectMocks
    private CmsGetAllMerchantHandler getAllMerchantHandler;

    @InjectMocks
    private CmsApproveMerchantHandler approveMerchantHandler;

    @InjectMocks
    private CmsDisapproveMerchantHandler disapproveMerchantHandler;

    @InjectMocks
    private CmsGetMerchantHandler cmsGetMerchantHandler;

    @Test
    public void getAllMerchantTest() {
        List<Merchant> merchants = Arrays.asList(
                new Merchant()
                        .setId("16f41db9-7768-4958-b1ea-c181245240b3")
                        .setAccountNo("accNo1")
                        .setName("Merchant 1")
                        .setApprovedAt(LocalDateTime.now())
                        .setRequestAt(LocalDateTime.now())
                        .setWebhookUrl("www.google.com")
                        .setStatus(MerchantStatus.ACTIVE),
                new Merchant()
                        .setId("16f41db9-7768-1234-b1ea-c181245240b3")
                        .setAccountNo("accNo2")
                        .setName("Merchant 2")
                        .setApprovedAt(LocalDateTime.now())
                        .setRequestAt(LocalDateTime.now())
                        .setWebhookUrl("www.facebook.com")
                        .setStatus(MerchantStatus.ACTIVE),
                new Merchant()
                        .setId("16f41db9-7768-4958-b1ea-c181245240c3")
                        .setAccountNo("accNo3")
                        .setName("Merchant 3")
                        .setApprovedAt(LocalDateTime.now())
                        .setRequestAt(LocalDateTime.now())
                        .setWebhookUrl("www.tiktok.com")
                        .setStatus(MerchantStatus.ACTIVE)
        );

        CmsGetAllMerchantRequest request = new CmsGetAllMerchantRequest()
                .setPageable(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id")))
                .setBusinessType(BusinessType.PERSONAL)
                .setUsername("username");

        Mockito
                .when(merchantService.getAllMerchant(request, request.getPageable()))
                .thenReturn(new PageImpl<>(merchants));


        PageResponse<MerchantResponse> response = getAllMerchantHandler.handle(request);
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.getPageNumber() == request.getPageable().getPageNumber());
        Assertions.assertTrue(response.getItems().size() == merchants.size());
    }

    @Test
    public void approveMerchantWhenMerchantNotFoundTest() {
        CmsApproveMerchantRequest request = new CmsApproveMerchantRequest("16f41db9-7768-4958-b1ea-c181245240c3");
        Mockito
                .when(merchantService.getMerchantById(request.getId()))
                .thenReturn(null);

        InternalException thrown = Assertions.assertThrows(
                InternalException.class,
                () -> approveMerchantHandler.handle(request),
                "Approve merchant, which is not existed");

        Assertions.assertTrue(thrown.getResponseCode().getCode() == 409);

    }

    @Test
    public void approveMerchantWhenMerchantStatusIsInvalidTest() {
        CmsApproveMerchantRequest request = new CmsApproveMerchantRequest("16f41db9-7768-4958-b1ea-c181245240c3");
        Mockito
                .when(merchantService.getMerchantById(request.getId()))
                .thenReturn(
                        new Merchant()
                                .setId("16f41db9-7768-4958-b1ea-c181245240c3")
                                .setAccountNo("accNo3")
                                .setName("Merchant 3")
                                .setApprovedAt(LocalDateTime.now())
                                .setRequestAt(LocalDateTime.now())
                                .setWebhookUrl("www.tiktok.com")
                                .setStatus(MerchantStatus.ACTIVE));

        InternalException thrown = Assertions.assertThrows(
                InternalException.class,
                () -> approveMerchantHandler.handle(request),
                "Approve merchant, which has invalid status");

        Assertions.assertTrue(thrown.getResponseCode().getCode() == 414);

    }

    @Test
    public void approveMerchantWhenSaveFailedTest() {
        CmsApproveMerchantRequest request = new CmsApproveMerchantRequest("16f41db9-7768-4958-b1ea-c181245240c3");
        Merchant merchant = new Merchant()
                .setId("16f41db9-7768-4958-b1ea-c181245240c3")
                .setAccountNo("accNo3")
                .setName("Merchant 3")
                .setApprovedAt(LocalDateTime.now())
                .setRequestAt(LocalDateTime.now())
                .setWebhookUrl("www.tiktok.com")
                .setStatus(MerchantStatus.LINKED_ACCOUNT);

        Mockito
                .when(merchantService.getMerchantById(request.getId()))
                .thenReturn(merchant);

        Mockito
                .when(merchantService.saveMerchant(merchant))
                .thenThrow(new RuntimeException("Failed"));

        String userId = "16f41db9-7768-4958-b1ea-c181245240c3";
        Administrator administrator = new Administrator()
                .setId("16f41db9-7768-4958-b1ea-c181245240c3")
                .setStatus(UserStatus.ACTIVE)
                .setUsername("admin")
                .setEmail("admin@gmail.com");

        Mockito
                .when(contextService.getLoggedInUserId())
                .thenReturn(Optional.of(userId));

        Mockito
                .when(adminService.getById(userId))
                .thenReturn(administrator);

        InternalException thrown = Assertions.assertThrows(
                InternalException.class,
                () -> approveMerchantHandler.handle(request),
                "Approve merchant failed"
        );

        Assertions.assertEquals(thrown.getResponseCode().getCode(), 1);
    }

    @Test
    public void approveMerchantSuccessTest() {
        CmsApproveMerchantRequest request = new CmsApproveMerchantRequest("16f41db9-7768-4958-b1ea-c181245240c3");
        Merchant merchant = new Merchant()
                .setId("16f41db9-7768-4958-b1ea-c181245240c3")
                .setAccountNo("accNo3")
                .setName("Merchant 3")
                .setApprovedAt(LocalDateTime.now())
                .setRequestAt(LocalDateTime.now())
                .setWebhookUrl("www.tiktok.com")
                .setStatus(MerchantStatus.LINKED_ACCOUNT);

        Mockito
                .when(merchantService.getMerchantById(request.getId()))
                .thenReturn(merchant);

        Mockito
                .when(merchantService.saveMerchant(merchant))
                .thenReturn(merchant);

        MerchantResponse merchantResponse = approveMerchantHandler.handle(request);
        Assertions.assertNotNull(merchantResponse);
        Assertions.assertEquals(merchantResponse.getId(), merchant.getId());
        Assertions.assertEquals(merchantResponse.getAccountNo(), merchant.getAccountNo());
        Assertions.assertEquals(merchantResponse.getStatus(), MerchantStatus.ACTIVE);
        Assertions.assertEquals(merchantResponse.getName(), merchant.getName());
        Assertions.assertEquals(merchantResponse.getApprovedAt(), merchant.getApprovedAt());
        Assertions.assertEquals(merchantResponse.getRequestAt(), merchant.getRequestAt());
        Assertions.assertEquals(merchantResponse.getWebhookUrl(), merchant.getWebhookUrl());
    }


    @Test
    public void disapproveMerchantWhenMerchantNotFoundTest() {
        CmsDisapproveMerchantRequest request = new CmsDisapproveMerchantRequest()
                .setId("16f41db9-7768-4958-b1ea-c181245240c3")
                .setReason("Invalid data");

        Mockito.when(merchantService.getMerchantById(request.getId()))
                .thenReturn(null);

        InternalException exception = Assertions.assertThrows(
                InternalException.class,
                () -> disapproveMerchantHandler.handle(request),
                "Throw exception"
        );

        Assertions.assertEquals(exception.getResponseCode().getCode(), 409);

    }

    @Test
    public void disapproveMerchantThrowExceptionWhenMerchantStatusInvalidTest() {
        CmsDisapproveMerchantRequest request = new CmsDisapproveMerchantRequest()
                .setId("16f41db9-7768-4958-b1ea-c181245240c3")
                .setReason("Invalid data");

        Merchant merchant = new Merchant()
                .setId("16f41db9-7768-4958-b1ea-c181245240c3")
                .setAccountNo("accNo3")
                .setName("Merchant 3")
                .setApprovedAt(LocalDateTime.now())
                .setRequestAt(LocalDateTime.now())
                .setWebhookUrl("www.tiktok.com")
                .setStatus(MerchantStatus.CREATED);

        Mockito.when(merchantService.getMerchantById(request.getId()))
                .thenReturn(merchant);

        InternalException exception = Assertions.assertThrows(
                InternalException.class,
                () -> disapproveMerchantHandler.handle(request),
                "Throw exception"
        );

        Assertions.assertEquals(exception.getResponseCode().getCode(), 414);

    }

    @Test
    public void disapproveMerchantThrowExceptionWhenSaveMerchantFailedTest() {
        CmsDisapproveMerchantRequest request = new CmsDisapproveMerchantRequest()
                .setId("16f41db9-7768-4958-b1ea-c181245240c3")
                .setReason("Invalid data");

        Merchant merchant = new Merchant()
                .setId("16f41db9-7768-4958-b1ea-c181245240c3")
                .setAccountNo("accNo3")
                .setName("Merchant 3")
                .setApprovedAt(LocalDateTime.now())
                .setRequestAt(LocalDateTime.now())
                .setWebhookUrl("www.tiktok.com")
                .setStatus(MerchantStatus.LINKED_ACCOUNT);

        Mockito.when(merchantService.getMerchantById(request.getId()))
                .thenReturn(merchant);

        Mockito.when(merchantService.saveMerchant(merchant))
                .thenThrow(new RuntimeException("Failed"));


        InternalException exception = Assertions.assertThrows(
                InternalException.class,
                () -> disapproveMerchantHandler.handle(request),
                "Throw exception"
        );

        Assertions.assertEquals(exception.getResponseCode().getCode(), 1);
    }

    @Test
    public void disapproveMerchantSuccessTest() {
        CmsDisapproveMerchantRequest request = new CmsDisapproveMerchantRequest()
                .setId("16f41db9-7768-4958-b1ea-c181245240c3")
                .setReason("Invalid data");

        Merchant merchant = new Merchant()
                .setId("16f41db9-7768-4958-b1ea-c181245240c3")
                .setAccountNo("accNo3")
                .setName("Merchant 3")
                .setApprovedAt(LocalDateTime.now())
                .setRequestAt(LocalDateTime.now())
                .setWebhookUrl("www.tiktok.com")
                .setStatus(MerchantStatus.LINKED_ACCOUNT);

        Mockito.when(merchantService.getMerchantById(request.getId()))
                .thenReturn(merchant);

        Mockito.when(merchantService.saveMerchant(merchant))
                .thenReturn(merchant);


        MerchantResponse merchantResponse = disapproveMerchantHandler.handle(request);

        Assertions.assertNotNull(merchantResponse);
        Assertions.assertEquals(merchantResponse.getId(), merchant.getId());
        Assertions.assertEquals(merchantResponse.getAccountNo(), merchant.getAccountNo());
        Assertions.assertEquals(merchantResponse.getStatus(), MerchantStatus.DENIED);
        Assertions.assertEquals(merchantResponse.getName(), merchant.getName());
        Assertions.assertEquals(merchantResponse.getApprovedAt(), merchant.getApprovedAt());
        Assertions.assertEquals(merchantResponse.getRequestAt(), merchant.getRequestAt());
        Assertions.assertEquals(merchantResponse.getWebhookUrl(), merchant.getWebhookUrl());
    }

    @Test
    public void getMerchantDetailThrowExceptionWhenMerchantNotFoundTest() {
        CmsGetMerchantRequest request = new CmsGetMerchantRequest("16f41db9-7768-4958-b1ea-c181245240c3");

        Mockito
                .when(merchantService.getMerchantById(request.getId()))
                .thenReturn(null);

        InternalException exception = Assertions.assertThrows(
                InternalException.class,
                () -> cmsGetMerchantHandler.handle(request),
                "Throw exception when merchant not found");

        Assertions.assertNotNull(exception);
        Assertions.assertEquals(exception.getResponseCode().getCode(), 409);
    }

    @Test
    public void getMerchantDetailSuccessTest() {
        CmsGetMerchantRequest request = new CmsGetMerchantRequest("16f41db9-7768-4958-b1ea-c181245240c3");

        Merchant merchant = new Merchant()
                .setId("16f41db9-7768-4958-b1ea-c181245240c3")
                .setAccountNo("accNo3")
                .setName("Merchant 3")
                .setApprovedAt(LocalDateTime.now())
                .setRequestAt(LocalDateTime.now())
                .setWebhookUrl("www.tiktok.com")
                .setStatus(MerchantStatus.CREATED);

        Mockito
                .when(merchantService.getMerchantById(request.getId()))
                .thenReturn(merchant);

        MerchantDetailResponse merchantResponse = cmsGetMerchantHandler.handle(request);

        Assertions.assertNotNull(merchantResponse);
        Assertions.assertEquals(merchantResponse.getId(), merchant.getId());
        Assertions.assertEquals(merchantResponse.getAccountNo(), merchant.getAccountNo());
        Assertions.assertEquals(merchantResponse.getStatus(), merchant.getStatus());
        Assertions.assertEquals(merchantResponse.getName(), merchant.getName());
        Assertions.assertEquals(merchantResponse.getApprovedAt(), merchant.getApprovedAt());
        Assertions.assertEquals(merchantResponse.getRequestAt(), merchant.getRequestAt());
        Assertions.assertEquals(merchantResponse.getWebhookUrl(), merchant.getWebhookUrl());

    }

}
