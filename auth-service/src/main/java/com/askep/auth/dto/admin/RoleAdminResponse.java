package com.askep.auth.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleAdminResponse {

    private Long id;

    private String name;

    @JsonProperty("created_at")
    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private LocalDateTime updatedAt;

}
