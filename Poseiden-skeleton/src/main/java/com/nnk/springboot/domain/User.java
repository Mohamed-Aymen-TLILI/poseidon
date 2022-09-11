package com.nnk.springboot.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Pattern(regexp ="^[A-Za-z]*$", message = "Input has to be text")
    @NotBlank(message = "FullName is mandatory")
    @Column(name = "fullname")
    private String fullname;

    @Pattern(regexp ="^[A-Za-z0-9_]*$", message = "Input has to be text and numerical")
    @NotBlank(message = "Username is mandatory")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Role is mandatory")
    @Column(name = "role")
    private String role = "USER";

    public User(String username, String fullname, String password, String role) {
    }
}
