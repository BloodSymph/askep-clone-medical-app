package com.askep.auth.exception.handler;

import com.askep.auth.exception.entity.ExceptionEntity;
import com.askep.auth.exception.exceptions.user.UserNotFoundException;
import com.askep.auth.exception.exceptions.user.UserEntityVersionNotValidException;
import com.askep.auth.exception.exceptions.user.EmailIsTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class UserExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionEntity> userNotFoundExceptionHandler(
            UserNotFoundException userNotFoundException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionEntity.setExceptionMessage(userNotFoundException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailIsTakenException.class)
    public ResponseEntity<ExceptionEntity> emailIsTakenExceptionHandler(
            EmailIsTakenException emailIsTakenException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionEntity.setExceptionMessage(emailIsTakenException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserEntityVersionNotValidException.class)
    public ResponseEntity<ExceptionEntity> userEntityVersionNotValidExceptionHandler(
            UserEntityVersionNotValidException userEntityVersionNotValidException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.CONFLICT.value());
        exceptionEntity.setExceptionMessage(userEntityVersionNotValidException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.CONFLICT);
    }

}
