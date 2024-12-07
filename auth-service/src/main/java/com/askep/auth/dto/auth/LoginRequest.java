package com.askep.auth.dto.auth;

import com.askep.auth.utils.validator.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email field shod not be empty!")
    @Length(max = 120, message = "Email field shod contains maximum {max} character!")
    @Email(message = "This field shod contains @ - character!")
    private String email;

    @NotBlank(message = "Password field shod not be empty!")
    @Length(max = 120, message = "Password field shod contains maximum {max} character!")
    @ValidPassword
    private String password;

    @NotEmpty
    @NotBlank(message = "Role name field shod not be empty!")
    @Length(max = 25, message = "Role name field shod contains maximum {max} character!")
    private String roleName;

}
