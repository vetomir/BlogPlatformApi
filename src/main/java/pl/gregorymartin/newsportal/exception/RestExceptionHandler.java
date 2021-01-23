package pl.gregorymartin.newsportal.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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


/*
    @ExceptionHandler(value = {AppUserServiceException.class, CategoryServiceException.class, CommentServiceException.class, PostServiceException.class, TagServiceException.class})
    protected ResponseEntity<ErrorDetails> handleException(Exception e, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getRequestURI());


        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
*/

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDetails> handleValidExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {
        System.out.println(e.getClass().getName());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), List.of(e.getBindingResult().getFieldError().getDefaultMessage()), request.getRequestURI());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorDetails> handleValidExceptions(ConstraintViolationException e, HttpServletRequest request) {
        System.out.println(e.getClass().getName());

        List<String> errors = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), errors, request.getRequestURI());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorDetails> handleOtherExceptions(HttpMessageNotReadableException e, HttpServletRequest request) {
        System.out.println(e.getClass().getName());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), List.of(e.getMessage()), request.getRequestURI());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorDetails> handleOtherExceptions(Exception e, HttpServletRequest request) {
        System.out.println(e.getClass().getName());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), List.of(e.getMessage()), request.getRequestURI());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }
}
