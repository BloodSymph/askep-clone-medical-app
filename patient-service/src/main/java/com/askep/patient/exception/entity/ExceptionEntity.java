package com.askep.patient.exception.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ExceptionEntity {

    private Integer statusCode;

    @NotBlank(message = "Exception message shod not be empty!")
    @Length(max = 255, message = "Exception message field shod contains maximum of {max} characters!")
    private String exceptionMessage;

    @DateTimeFormat(pattern = "E, dd MM yyyy HH:mm:ss z")
    private Date exceptionTimeStamp;

}
