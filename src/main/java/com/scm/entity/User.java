package com.scm.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    private String userId;
    @Column(name = "user_name", nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Getter(AccessLevel.NONE)
    private String password;
    private String phoneNumber;
    @Getter(AccessLevel.NONE)
    private Boolean enabled;
    private Boolean emailVerified=false;
    private Boolean phoneVerified=false;

// SELF, GOOGLE, FACEBOOK, GITHUB
    @Enumerated(value = EnumType.STRING)
    private Provider provider=Provider.SELF;
    private  String providerId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public Boolean getEnabled() {
        return enabled = true;
    }
}
