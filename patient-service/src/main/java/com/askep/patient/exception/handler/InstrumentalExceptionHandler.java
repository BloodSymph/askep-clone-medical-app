package com.askep.patient.exception.handler;

import com.askep.patient.exception.entity.ExceptionEntity;
import com.askep.patient.exception.exceptions.instrumental.InstrumentalNotFoundedException;
import com.askep.patient.exception.exceptions.instrumental.InstrumentalVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class InstrumentalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InstrumentalNotFoundedException.class)
    public ResponseEntity<ExceptionEntity> instrumentalNotFoundExceptionHandler(
            InstrumentalNotFoundedException instrumentalNotFoundedException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionEntity.setExceptionMessage(instrumentalNotFoundedException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InstrumentalVersionNotValidException.class)
    public ResponseEntity<ExceptionEntity> instrumentalVersionNotValidExceptionHandler(
            InstrumentalVersionNotValidException instrumentalVersionNotValidException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionEntity.setExceptionMessage(instrumentalVersionNotValidException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.BAD_REQUEST);
    }

}
