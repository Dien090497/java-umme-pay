package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.dtos.merchant.request.*;
import vn.unicloud.umeepay.dtos.merchant.response.*;
import vn.unicloud.umeepay.dtos.model.MerchantDto;
import vn.unicloud.umeepay.entity.Credential;
import vn.unicloud.umeepay.entity.Merchant;
import vn.unicloud.umeepay.entity.User;
import vn.unicloud.umeepay.enums.KeyStatus;
import vn.unicloud.umeepay.enums.MerchantStatus;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.CredentialRepository;
import vn.unicloud.umeepay.repository.MerchantRepository;
import vn.unicloud.umeepay.repository.UserRepository;
import vn.unicloud.umeepay.utils.CommonUtils;
import vn.unicloud.umeepay.utils.ModelMapperUtils;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class MerchantService {

    @Value("${umeepay.public-key}")
    private String umeePayPublicKey;

    private final MerchantRepository merchantRepository;

    private final UserRepository userRepository;

    private final CredentialRepository credentialRepository;

    @Transactional
    public CreateMerchantResponse createMerchant(CreateMerchantRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
            () -> {throw new InternalException(ResponseCode.USER_NOT_FOUND);}
        );
        Merchant merchant = merchantRepository.findByUserId(request.getUserId());
        if (merchant != null) {
            throw new InternalException(ResponseCode.MERCHANT_ALREADY_CREATED);
        }
        merchant = Merchant.builder()
            .accountNo(request.getAccountId())
            .status(MerchantStatus.CREATED)
//            .user(user)
            .build();
        merchant = merchantRepository.save(merchant);
        Credential credential = Credential.builder()
            .clientId(UUID.randomUUID().toString())
            .secretKey(CommonUtils.getEncryptKey(128))
            .status(KeyStatus.ACTIVE)
            .publicKey(umeePayPublicKey)
            .merchant(merchant)
            .build();
        credentialRepository.save(credential);
        merchant = merchantRepository.findByUserId(request.getUserId());
        MerchantDto merchantDto = ModelMapperUtils.mapper(merchant, MerchantDto.class);
        CreateMerchantResponse response =  new CreateMerchantResponse(true, merchantDto);
        return  response;
    }

    public GetMerchantResponse getMerchant(GetMerchantRequest request) {
        Merchant merchant = merchantRepository.findByUserId(request.getUserId());
        if (merchant == null) {
            throw new InternalException(ResponseCode.MERCHANT_NOT_FOUND);
        }
        return new GetMerchantResponse(true, ModelMapperUtils.mapper(merchant, MerchantDto.class));
    }

    public UpdateMerchantResponse updateMerchant(UpdateMerchantRequest request) {
        Merchant merchant = merchantRepository.findByUserId(request.getUserId());
        if (merchant == null) {
            throw new InternalException(ResponseCode.MERCHANT_NOT_FOUND);
        }
        if (StringUtils.isNoneBlank(request.getMerchantName())) {
        }
        if (StringUtils.isNoneBlank(request.getAccountNo())) {
            merchant.setAccountNo(request.getAccountNo());
        }
        merchantRepository.save(merchant);
        return new UpdateMerchantResponse(true);
    }

    public GetMerchantCredentialResponse getMerchantCredential(GetMerchantCredentialRequest request) {
        return null;
    }

    public UpdateWebhookResponse updateWebhook(UpdateWebhookRequest request) {
        Merchant merchant = merchantRepository.findByUserId(request.getUserId());
        if (merchant == null) {
            throw new InternalException(ResponseCode.MERCHANT_NOT_FOUND);
        }
        merchant.setWebhookUrl(request.getUrl());
        merchant.setWebhookApiKey(request.getApiKey());
        merchantRepository.save(merchant);
        return new UpdateWebhookResponse(true);
    }
}
