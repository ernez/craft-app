package com.ernez.craftapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    @NotBlank
    @NotNull
    @Size(min = 2, max = 20, message = "Invalid First Name: Must be of 2 - 20 characters")
    private String firstName;
    @NotBlank
    @NotNull
    @Size(min = 2, max = 20, message = "Invalid Last Name: Must be of 2 - 20 characters")
    private String lastName;
    private String gender;
    private String[] skills;
    @Email(message = "Invalid email")
    private String email;
    private Integer age;
    private String status;
    private String address; // TODO link to Contact of Address type
    private String city;
    private String postcode;
    private String phoneNumber; // TODO link to Contact of Telephone type
    private String password;
}
