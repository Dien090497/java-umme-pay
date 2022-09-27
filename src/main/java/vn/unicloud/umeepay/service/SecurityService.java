package vn.unicloud.umeepay.service;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.core.BaseRequestData;
import vn.unicloud.umeepay.dtos.payment.request.CancelTransactionRequest;
import vn.unicloud.umeepay.entity.Credential;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;
import vn.unicloud.umeepay.repository.CredentialRepository;
import vn.unicloud.umeepay.utils.CommonUtils;

@Service
@Log4j2
public class SecurityService {

    @Autowired
    private CredentialRepository credentialRepository;

    public <T extends BaseRequestData> T authenticate(BaseRequestData requestData, Class<T> tClass) {
        // check timestamp
        // check signature
        try {
            Credential credential = credentialRepository.findById(requestData.getClientId()).orElseThrow(
                () -> {
                    throw new InternalException(ResponseCode.INVALID_KEY_ID);
                }
            );
            String signature = CommonUtils.md5(requestData.getClientId() +
                requestData.getTimestamp() +
                requestData.getEncryptedData() +
                credential.getSecretKey());
            if (signature != null && signature.equals(requestData.getSignature())) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new InternalException(ResponseCode.INVALID_DATA);
    }

    public void simpleAuthenticate(BaseRequestData request) {
        Credential credential = credentialRepository.findById(request.getKeyId()).orElseThrow(
            () -> {throw new InternalException(ResponseCode.INVALID_KEY_ID);}
        );
        if (!credential.getSecretKey().equals(request.getSecretKey())) {
            throw new InternalException(ResponseCode.AUTHORIZATION_FAILED);
        }
        log.debug("credential: {}", credential);
        request.setCredential(credential);
    }
}
