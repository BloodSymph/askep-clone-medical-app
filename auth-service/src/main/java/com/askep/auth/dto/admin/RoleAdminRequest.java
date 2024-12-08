package com.askep.auth.dto.admin;

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
public class RoleAdminRequest {

    @NotBlank(message = "Role name field shod not be empty!")
    @Length(max = 120, message = "Role name field shod contains maximum {max} character!")
    private String name;

    @NotNull(message = "Version field shod not contains null value!")
    private Long version;

}
