package vn.unicloud.umeepay.config;


import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.unicloud.umeepay.core.ResponseBase;
import vn.unicloud.umeepay.exception.InternalException;

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
        return new ResponseEntity<>(new ResponseBase<>(1, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleArgumentInvalidException(MethodArgumentNotValidException e) {
        StringBuilder errors = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = "[" + fieldName + ": " + error.getDefaultMessage() + "]";
            errors.append(errorMessage);
            errors.append(", ");
        });
        String errorMessage = "Invalid argument: " + errors;
        log.error("Invalid argument: {}", errors);
        return new ResponseEntity<>(new ResponseBase<>(1, errorMessage), HttpStatus.BAD_REQUEST);
    }
}
