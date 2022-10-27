package vn.unicloud.umeepay.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.enums.ResponseCode;
import vn.unicloud.umeepay.exception.InternalException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
@Log4j2
public class BaseExceptionController {

    @ExceptionHandler({InternalException.class})
    public ResponseEntity<?> handleBusinessException(InternalException e) {
        log.error("Business error {}", e.getMessage());
        return ResponseEntity.ok(new ResponseBase<>(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException(Exception e) {
        log.error("", e);
        return new ResponseEntity<>(new ResponseBase<>(ResponseCode.COMMON_ERROR.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            BindException.class
    })
    public ResponseEntity<?> handleArgumentInvalidException(BindException e) {
        Map<String, List<String>> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, List.of(Optional.ofNullable(errorMessage).orElse("")));
        });

        ResponseBase<?> responseBase = new ResponseBase<>(errors);
        responseBase.setCode(ResponseCode.INVALID_PARAM.getCode());
        responseBase.setMessage(ResponseCode.INVALID_PARAM.getMessage());

        return new ResponseEntity<>(responseBase, HttpStatus.BAD_REQUEST);
    }

}
