package vn.unicloud.umeepay.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.unicloud.umeepay.dtos.merchant.request.CreateMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.request.GetMerchantCredentialRequest;
import vn.unicloud.umeepay.dtos.merchant.request.GetMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.request.UpdateMerchantRequest;
import vn.unicloud.umeepay.dtos.merchant.response.CreateMerchantResponse;
import vn.unicloud.umeepay.dtos.merchant.response.GetMerchantCredentialResponse;
import vn.unicloud.umeepay.dtos.merchant.response.GetMerchantResponse;
import vn.unicloud.umeepay.dtos.merchant.response.UpdateMerchantResponse;
import vn.unicloud.umeepay.dtos.model.MerchantDto;
import vn.unicloud.umeepay.dtos.request.ClientLoginRequest;
import vn.unicloud.umeepay.dtos.response.AccessTokenResponseCustom;
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

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Log4j2
public class MerchantService {

    @Value("${umeepay.public-key}")
    private String umeePayPublicKey;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CredentialRepository credentialRepository;

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
            .accountNo(request.getAccountNo())
            .createDateTime(LocalDateTime.now())
            .status(MerchantStatus.ACTIVE)
            .name(request.getMerchantName())
            .user(user)
            .build();
        merchant = merchantRepository.save(merchant);
        Credential credential = Credential.builder()
            .clientId(UUID.randomUUID().toString())
            .createDateTime(LocalDateTime.now())
            .secretKey(CommonUtils.getEncryptKey(128))
            .status(KeyStatus.ACTIVE)
            .publicKey(umeePayPublicKey)
            .merchant(merchant)
            .build();
        credentialRepository.save(credential);
        merchant = merchantRepository.findByUserId(request.getUserId());
        MerchantDto merchantDto = ModelMapperUtils.mapper(merchant, MerchantDto.class);
        return new CreateMerchantResponse(true, merchantDto);
    }

    public GetMerchantResponse getMerchant(GetMerchantRequest request) {
        Merchant merchant = merchantRepository.findByUserId(request.getUserId());
        if (merchant == null) {
            throw new InternalException(ResponseCode.MERCHANT_NOT_FOUND);
        }
        return new GetMerchantResponse(true, ModelMapperUtils.mapper(merchant, MerchantDto.class));
    }

    public UpdateMerchantResponse updateMerchant(UpdateMerchantRequest request) {
        return null;
    }

    public GetMerchantCredentialResponse getMerchantCredential(GetMerchantCredentialRequest request) {
        return null;
    }
}
