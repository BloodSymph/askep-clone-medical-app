package com.askep.medpersonal.dto.client;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedPersonalProfileClientRequest {

    @NotBlank(message = "First name field shod not be empty!")
    @Length(max = 120, message = "First name field shod contains maximum {max} character!")
    private String firstName;

    @NotBlank(message = "Last name field shod not be empty!")
    @Length(max = 120, message = "Last name field shod contains maximum {max} character!")
    private String lastName;

    @NotBlank(message = "Phone number field shod not be empty!")
    @Length(max = 120, message = "Phone number field shod contains maximum {max} character!")
    private String phoneNumber;

    @NotBlank(message = "Email field shod not be empty!")
    @Length(max = 120, message = "Email field shod contains maximum {max} character!")
    @Email(message = "This field shod contains @ - character!")
    private String email;

    @NotBlank(message = "Address field shod not be empty!")
    @Length(max = 120, message = "Address field shod contains maximum {max} character!")
    private String address;

    @NotBlank(message = "Specialization field shod not be empty!")
    @Length(max = 120, message = "Specialization field shod contains maximum {max} character!")
    private String specialization;

    @NotNull(message = "Version field shod not contains null value!")
    private Long version;

}
