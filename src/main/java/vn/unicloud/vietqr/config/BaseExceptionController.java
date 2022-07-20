package vn.unicloud.vietqr.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.unicloud.vietqr.core.ResponseBase;
import vn.unicloud.vietqr.exception.InternalException;

@RestControllerAdvice
public class BaseExceptionController {
    private static final Logger log = LoggerFactory.getLogger(BaseExceptionController.class);

    public BaseExceptionController() {
    }

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
