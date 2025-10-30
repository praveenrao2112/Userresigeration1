package com.example.Userresigeration.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data                 // ✅ generates getters/setters
@NoArgsConstructor
@AllArgsConstructor
@Builder              // ✅ generates builder() and field methods
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;      // ✅ builder().name()
    private String email;     // ✅ builder().email()
    private String password;
    private String role;
    private boolean enabled;
    private boolean oauth2;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isOauth2() {
        return oauth2;
    }

    public void setOauth2(boolean oauth2) {
        this.oauth2 = oauth2;
    }
}
