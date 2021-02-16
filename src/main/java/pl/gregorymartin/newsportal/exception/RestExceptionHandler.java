package pl.gregorymartin.newsportal.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {
    Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDetails> handleValidExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {
        logger.warn("{} , message: '{}'", e.getClass().getSimpleName() , e.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), List.of(e.getBindingResult().getFieldError().getDefaultMessage()), request.getRequestURI());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorDetails> handleValidExceptions(ConstraintViolationException e, HttpServletRequest request) {
        List<String> errors = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        logger.warn("{} , message: '{}'", e.getClass().getSimpleName() , errors);

        ErrorDetails errorDetails = new ErrorDetails(new Date(), errors, request.getRequestURI());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorDetails> handleOtherExceptions(HttpMessageNotReadableException e, HttpServletRequest request) {
        logger.warn("{} , message: '{}'", e.getClass().getSimpleName() , e.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), List.of(e.getMessage()), request.getRequestURI());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalAccessException.class)
    protected ResponseEntity<ErrorDetails> handleOtherExceptions(IllegalAccessException e, HttpServletRequest request) {
        logger.warn("{} , message: '{}'", e.getClass().getSimpleName() , e.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), List.of(e.getMessage()), request.getRequestURI());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(LockedException.class)
    protected ResponseEntity<ErrorDetails> handleOtherExceptions(LockedException e, HttpServletRequest request) {
        logger.warn("{} , message: '{}'", e.getClass().getSimpleName(), "User account is locked, please check Your email address");

        try{
            ErrorDetails errorDetails = new ErrorDetails(new Date(), List.of("User account is locked, please check Your email address"), request.getRequestURI());

            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }catch (Exception f){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<ErrorDetails> handleOtherExceptions(ExpiredJwtException e, HttpServletRequest request) {
        logger.warn("{} , message: '{}'", e.getClass().getSimpleName(), e.getMessage());

        try{
            ErrorDetails errorDetails = new ErrorDetails(new Date(), List.of(e.getMessage()), request.getRequestURI());

            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }catch (Exception f){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorDetails> handleOtherExceptions(Exception e, HttpServletRequest request) {
        logger.warn("{} , message: '{}'", e.getClass().getSimpleName(), e.getMessage());

        try{
            ErrorDetails errorDetails = new ErrorDetails(new Date(), List.of(e.getMessage()), request.getRequestURI());

            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }catch (Exception f){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }
}
