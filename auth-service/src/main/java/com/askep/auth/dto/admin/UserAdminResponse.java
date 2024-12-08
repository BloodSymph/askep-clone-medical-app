package com.askep.auth.dto.admin;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserAdminResponse {

    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String email;

    @JsonProperty("created_at")
    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private LocalDateTime updatedAt;

}
