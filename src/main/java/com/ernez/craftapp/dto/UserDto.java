package com.ernez.craftapp.dto;

import com.ernez.craftapp.domain.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String[] skills;
    private String email;
    private Integer age;
    private String status;
    private String address; // TODO link to Contact of Address type
    private String city;
    private String postcode;
    private String phoneNumber; // TODO link to Contact of Telephone type
    private String userName;
    private String password;
}
