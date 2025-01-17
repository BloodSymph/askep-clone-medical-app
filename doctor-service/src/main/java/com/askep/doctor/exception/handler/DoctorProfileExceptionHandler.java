package com.askep.doctor.exception.handler;

import com.askep.doctor.exception.entity.ExceptionEntity;
import com.askep.doctor.exception.exceptions.DoctorEntityVersionNotValidException;
import com.askep.doctor.exception.exceptions.DoctorProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class DoctorProfileExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DoctorProfileNotFoundException.class)
    public ResponseEntity<ExceptionEntity> doctorProfileNotFoundedExceptionHandler(
            DoctorProfileNotFoundException doctorProfileNotFoundException){
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionEntity.setExceptionMessage(doctorProfileNotFoundException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DoctorEntityVersionNotValidException.class)
    public ResponseEntity<ExceptionEntity> doctorEntityVersionNotValidExceptionHandler(
            DoctorEntityVersionNotValidException doctorEntityVersionNotValidException){
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionEntity.setExceptionMessage(doctorEntityVersionNotValidException.getMessage());
        exceptionEntity.setExceptionTimeStamp(new Date());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.BAD_REQUEST);
    }

}
