package com.askep.auth.exception.handler;

import com.askep.auth.exception.entity.ExceptionEntity;
import com.askep.auth.exception.exceptions.role.RoleEntityVersionNotValidException;
import com.askep.auth.exception.exceptions.role.RoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Date;

@RestControllerAdvice
public class RoleExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ExceptionEntity> roleNotFoundExceptionHandler(
            RoleNotFoundException roleNotFoundException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionEntity.setExceptionMessage(roleNotFoundException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(RoleEntityVersionNotValidException.class)
    public ResponseEntity<ExceptionEntity> roleEntityVersionNotValidExceptionHandler(
            RoleEntityVersionNotValidException roleEntityVersionNotValidException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.CONFLICT.value());
        exceptionEntity.setExceptionMessage(roleEntityVersionNotValidException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.CONFLICT);
    }

}
