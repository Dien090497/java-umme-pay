package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.cache.CredentialCache;
import vn.unicloud.umeepay.entity.merchant.Credential;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.CredentialRepository;
import vn.unicloud.umeepay.repository.MerchantRepository;
import vn.unicloud.umeepay.utils.ModelMapperUtils;

@Service
@Log4j2
@RequiredArgsConstructor
public class CredentialService {
    private final CredentialRepository credentialRepository;
    private final RedisService redisService;
    private final MerchantRepository merchantRepository;
    public Credential getCredentialCacheById(String id) {
        CredentialCache credentialCache = redisService.getValue("credential."+id, CredentialCache.class);
        Credential credential = null;
        if (credentialCache == null) {
            credential = credentialRepository.findById(id).orElseThrow(
                () -> {
                    throw new InternalException(ResponseCode.INVALID_KEY_ID);
                }
            );
            credentialCache = ModelMapperUtils.mapper(credential, CredentialCache.class);
            redisService.setValue("credential."+id, credentialCache);
            return credential;
        }
        credential = ModelMapperUtils.mapper(credentialCache, Credential.class);
        return credential;
    }

}
