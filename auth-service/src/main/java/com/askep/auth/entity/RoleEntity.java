package com.askep.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Role")
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roles_name", nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<UserEntity> users;

    @CreationTimestamp
    @Column(name = "roles_created")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "roles_updatetAt")
    private LocalDateTime updatedAt;

    @Version
    @Column(name = "roles_version", nullable = false, unique = true)
    private Long version;

}
