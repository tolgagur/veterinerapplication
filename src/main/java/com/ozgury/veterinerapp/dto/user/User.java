package com.ozgury.veterinerapp.dto.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "id")
    private Integer userId;

    @NotBlank
    @Email(regexp = "[a-zA-Z0-9_]+@[a-zA-Z0-9]+\\.[a-zA-Z]+", message = "email formatinda değil")
    private String email;

    @NotBlank
    @Size(min = 5, message = "Şifre 5 karakterden az olamaz")
    private String password;

    private String role;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }




}
