package com.ernez.craftapp.service;

import com.ernez.craftapp.domain.Address;
import com.ernez.craftapp.domain.User;
import com.ernez.craftapp.domain.enumeration.UserStatus;
import com.ernez.craftapp.dto.UserDto;
import com.ernez.craftapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserDto create(UserDto userDto) {
        log.debug("Request to create Customer : {}", userDto);
        return mapToDto(
                this.userRepository.save(
                    User.builder()
                        .firstName(userDto.getFirstName())
                        .lastName(userDto.getLastName())
                        .email(userDto.getEmail())
                        .address(new Address(userDto.getAddress(), userDto.getCity(), userDto.getPostcode()))
                        .telephone(userDto.getTelephone())
                        .enabled(userDto.isEnabled())
                        .status(UserStatus.valueOf(userDto.getStatus()))
                        .build()
                )
        );
    }

    public List<UserDto> findAll() {
        log.debug("Request to get all Customers");
        return this.userRepository.findAll()
                .stream()
                .map(UserService::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        log.debug("Request to get Customer : {}", id);
        return this.userRepository.findById(id).map(UserService::mapToDto).orElse(null);
    }

    public List<UserDto> findAllActive() {
        log.debug("Request to get all Customers");
        return this.userRepository.findAllByEnabled(true)
                .stream()
                .map(UserService::mapToDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> findAllInactive() {
        log.debug("Request to get all Customers");
        return this.userRepository.findAllByEnabled(false)
                .stream()
                .map(UserService::mapToDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        log.debug("Request to delete Customer : {}", id);

        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find Customer with id " + id));

        user.setEnabled(false);
        this.userRepository.save(user);
    }

    @Nullable
    public static UserDto mapToDto(User user) {
        if (user != null) {
            return UserDto.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .telephone(user.getTelephone())
                    .enabled(user.isEnabled())
                    .build();
        }
        return null;
    }

}
