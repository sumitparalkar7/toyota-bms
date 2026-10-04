package com.toyota.bms.exception;
import org.springframework.dao.DataIntegrityViolationException; import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.*; import java.util.*;
@RestControllerAdvice public class
GlobalExceptionHandler {record ApiError(
        int status,
        String error,
        String message,
        LocalDateTime timestamp,
        Map<String,String> validationErrors){

} @ExceptionHandler(MethodArgumentNotValidException.class)
ResponseEntity<ApiError> validation(MethodArgumentNotValidException e)
{Map<String,String> m=new LinkedHashMap<>();
    e.getBindingResult()
            .getFieldErrors()
            .forEach(x->m.put(x.getField(),x.getDefaultMessage()));
    return ResponseEntity.badRequest()
            .body(new ApiError(400,"Validation Failed",
                    "Please correct the invalid fields.",
                    LocalDateTime.now(),m));}
    @ExceptionHandler(ApiExceptions.NotFound.class)
    ResponseEntity<ApiError> nf(ApiExceptions.NotFound e){
    return ResponseEntity.status(404).body(new ApiError
            (404,"Not Found",
                    e.getMessage(),
                    LocalDateTime.now(),
                    Map.of()));}
    @ExceptionHandler({ApiExceptions.Conflict.class,
            DataIntegrityViolationException.class})
    ResponseEntity<ApiError> cf(Exception e){
    return ResponseEntity.status(409)
            .body(new ApiError(409,"Conflict","A record for this date may already exist.",
                    LocalDateTime.now(),Map.of()));}
    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiError> ge(Exception e){
    return ResponseEntity.status(500)
            .body(new ApiError(500,"Internal Server Error","Unexpected server error.",
                    LocalDateTime.now(),Map.of()));}}
