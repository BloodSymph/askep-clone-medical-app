package com.askep.auth.dto.admin;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAdminResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
