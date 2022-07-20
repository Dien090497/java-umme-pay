package vn.unicloud.vietqr.service;

import org.springframework.stereotype.Service;
import vn.unicloud.vietqr.model.TransactionCallback;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CallbackService {

    private ConcurrentHashMap<String, TransactionCallback> hashMap;

    public CallbackService() {
        hashMap = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, TransactionCallback> getHashMap() {
        return hashMap;
    }
}
