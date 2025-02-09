package com.askep.patient.exception.handler;

import com.askep.patient.exception.entity.ExceptionEntity;
import com.askep.patient.exception.exceptions.diagnosis.DiagnosisNotFoundedException;
import com.askep.patient.exception.exceptions.diagnosis.DiagnosisVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class DiagnosisExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DiagnosisNotFoundedException.class)
    public ResponseEntity<ExceptionEntity> diagnosisNotFoundExceptionHandler(
            DiagnosisNotFoundedException diagnosisNotFoundedException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionEntity.setExceptionMessage(diagnosisNotFoundedException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DiagnosisVersionNotValidException.class)
    public ResponseEntity<ExceptionEntity> diagnosisVersionNotValidExceptionHandler(
            DiagnosisVersionNotValidException diagnosisVersionNotValidException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionEntity.setExceptionMessage(diagnosisVersionNotValidException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.BAD_REQUEST);
    }

}
