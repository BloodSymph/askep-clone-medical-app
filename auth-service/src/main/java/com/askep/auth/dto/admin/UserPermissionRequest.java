package com.askep.auth.dto.admin;

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
public class UserPermissionRequest {

    @NotBlank(message = "Email field shod not be empty!")
    @Length(max = 120, message = "Email field shod contains maximum {max} character!")
    @Email(message = "This field shod contains @ - character!")
    private String email;

    @NotEmpty
    @NotBlank(message = "Role name field shod not be empty!")
    @Length(max = 25, message = "Role name field shod contains maximum")
    private String roleName;

}
