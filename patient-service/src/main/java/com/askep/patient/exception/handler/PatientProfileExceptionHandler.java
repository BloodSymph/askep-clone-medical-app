package com.askep.patient.exception.handler;

import com.askep.patient.exception.entity.ExceptionEntity;
import com.askep.patient.exception.exceptions.patient.PatientProfileNotFoundedException;
import com.askep.patient.exception.exceptions.patient.PatientProfileVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class PatientProfileExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PatientProfileNotFoundedException.class)
    public ResponseEntity<ExceptionEntity> patientProfileNotFoundExceptionHandler(
            PatientProfileNotFoundedException patientProfileNotFoundedException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionEntity.setExceptionMessage(patientProfileNotFoundedException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PatientProfileVersionNotValidException.class)
    public ResponseEntity<ExceptionEntity> patientVersionNotValidException(
            PatientProfileVersionNotValidException patientProfileVersionNotValidException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionEntity.setExceptionMessage(patientProfileVersionNotValidException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.BAD_REQUEST);
    }

}
