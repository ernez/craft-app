package com.ernez.craftapp.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    @NotBlank(message = "Invalid First Name: Empty first name")
    @NotNull(message = "Invalid First Name: First Name is NULL")
    @Size(min = 2, max = 20, message = "Invalid First Name: Must be of 2 - 20 characters")
    private final String firstName;
    @NotBlank(message = "Invalid Last Name: Empty last name")
    @NotNull(message = "Invalid Last Name: Last Name is NULL")
    @Size(min = 2, max = 50, message = "Invalid Last Name: Must be of 2 - 50 characters")
    private final String lastName;
    @Email(message = "Invalid email")
    private final String email;
    @NotBlank(message = "Invalid Password: Empty password")
    @NotNull(message = "Invalid Password: Password is NULL")
    private final String password;
}
