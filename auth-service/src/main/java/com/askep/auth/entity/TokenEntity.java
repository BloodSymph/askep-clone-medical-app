package com.askep.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Token")
@Table(name = "tokens")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tokens_access")
    private String accessToken;

    @Column(name = "tokens_refresh")
    private String refreshToken;

    @Column(name = "tokens_is_logged_out")
    private Boolean isLoggedOut;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @CreationTimestamp
    @Column(name = "tokens_created")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "tokens_updatetAt")
    private LocalDateTime updatedAt;

}
