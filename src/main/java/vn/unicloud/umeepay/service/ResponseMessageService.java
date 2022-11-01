package vn.unicloud.umeepay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.unicloud.umeepay.entity.ResponseMessage;
import vn.unicloud.umeepay.repository.ResponseMessageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResponseMessageService {

    private final ResponseMessageRepository messageRepository;

    public ResponseMessage saveMessage(ResponseMessage message) {
        try {
            return messageRepository.save(message);
        } catch (Exception ex) {
            log.error("Save response message failed, {}", ex.getMessage());
        }
        return null;
    }

    public ResponseMessage deleteMessage(ResponseMessage message) {
        try {
            messageRepository.delete(message);
            return message;
        } catch (Exception ex) {
            log.error("Delete response message failed, {}", ex.getMessage());
        }
        return null;
    }

    public ResponseMessage getById(Long id) {
        try {
            return messageRepository.findById(id).orElse(null);
        } catch (Exception ex) {
            log.error("Delete response message failed, {}", ex.getMessage());
        }
        return null;
    }

    public ResponseMessage getByCode(Integer code) {
        try {
            return messageRepository.findFirstByCode(code).orElse(null);
        } catch (Exception ex) {
            log.error("Delete response message failed, {}", ex.getMessage());
        }
        return null;
    }

    public List<ResponseMessage> getAll() {
        try {
            return messageRepository.findAll();
        } catch (Exception ex) {
        }
        return new ArrayList<>();
    }

}
