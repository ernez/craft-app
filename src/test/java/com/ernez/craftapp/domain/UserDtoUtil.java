package com.ernez.craftapp.domain;

import com.ernez.craftapp.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserDtoUtil {
    public static UserDto createDummyUserDto(Long id, String firstName, String lastName) {
        UserDto userDto = UserDto.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();
        return userDto;
    }

    public static List<UserDto> createDummyUserDtoList() {
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(createDummyUserDto(1L, "Ernez", "Catovic"));
        userDtos.add(createDummyUserDto(2L, "Ena", "Catovic"));
        return userDtos;
    }
}
