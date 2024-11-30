package com.askep.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.asm.IProgramElement;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "User")
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "users_first_name", nullable = false)
    private String firstName;

    @Column(name = "users_last_name", nullable = false)
    private String lastName;

    @Column(name = "users_phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "users_email", nullable = false, unique = true)
    private String email;

    @Column(name = "users_password", nullable = false, unique = true, length = 2048)
    private String password;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "users_roles",
            joinColumns = {
                    @JoinColumn(name = "users_email", referencedColumnName = "users_email")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "roles_name", referencedColumnName = "roles_name")
            }
    )
    private List<RoleEntity> roles;

    @JsonIgnore
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.REMOVE
    )
    private List<TokenEntity> tokens;

    @CreationTimestamp
    @Column(name = "users_created")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "users_updatetAt")
    private LocalDateTime updatedAt;

    @Version
    @Column(name = "users_version", nullable = false, unique = true)
    private Long version;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(
                        roleEntity -> new SimpleGrantedAuthority(
                                roleEntity.getName()
                        )
                )
                .collect(
                        Collectors.toList()
                );
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
