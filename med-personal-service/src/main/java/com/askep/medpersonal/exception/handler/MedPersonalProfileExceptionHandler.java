package com.askep.medpersonal.exception.handler;

import com.askep.medpersonal.exception.entity.ExceptionEntity;
import com.askep.medpersonal.exception.exceptions.MedPersonalProfileVersionNotValidException;
import com.askep.medpersonal.exception.exceptions.MedPersonalProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class MedPersonalProfileExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MedPersonalProfileNotFoundException.class)
    public ResponseEntity<ExceptionEntity> doctorProfileNotFoundedExceptionHandler(
            MedPersonalProfileNotFoundException medPersonalProfileNotFoundException){
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionEntity.setExceptionMessage(medPersonalProfileNotFoundException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MedPersonalProfileVersionNotValidException.class)
    public ResponseEntity<ExceptionEntity> doctorEntityVersionNotValidExceptionHandler(
            MedPersonalProfileVersionNotValidException medPersonalProfileVersionNotValidException){
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionEntity.setExceptionMessage(medPersonalProfileVersionNotValidException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.BAD_REQUEST);
    }

}
