package com.askep.patient.exception.handler;

import com.askep.patient.exception.entity.ExceptionEntity;
import com.askep.patient.exception.exceptions.analysis.AnalysisNotFoundedException;
import com.askep.patient.exception.exceptions.analysis.AnalysisVersionNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class AnalysisExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AnalysisNotFoundedException.class)
    public ResponseEntity<ExceptionEntity> analysisNotFoundExceptionHandler(
            AnalysisNotFoundedException analysisNotFoundedException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionEntity.setExceptionMessage(analysisNotFoundedException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AnalysisVersionNotValidException.class)
    public ResponseEntity<ExceptionEntity> analysisVersionNotValidException(
            AnalysisVersionNotValidException analysisVersionNotValidException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionEntity.setExceptionMessage(analysisVersionNotValidException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.BAD_REQUEST);
    }

}
