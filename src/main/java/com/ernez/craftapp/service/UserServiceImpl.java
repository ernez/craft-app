//package com.ernez.craftapp.service;
//
//import com.ernez.craftapp.domain.Address;
//import com.ernez.craftapp.domain.User;
//import com.ernez.craftapp.domain.enumeration.EUserStatus;
//import com.ernez.craftapp.dto.UserDto;
//import com.ernez.craftapp.exception.DaoException;
//import com.ernez.craftapp.repository.AppUserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.lang.Nullable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Slf4j
//@Service
//@Transactional
//public class UserServiceImpl implements UserService {
//
//    private final AppUserRepository userRepository;
//
//    public UserDto create(UserDto userDto) {
//        log.debug("Request to create Customer : {}", userDto);
//        return mapToDto(
//                this.userRepository.save(
//                    User.builder()
//                        .firstName(userDto.getFirstName())
//                        .lastName(userDto.getLastName())
//                        .email(userDto.getEmail())
//                        .address(new Address(userDto.getAddress(), userDto.getCity(), userDto.getPostcode()))
//                        .phoneNumber(userDto.getPhoneNumber())
//                        .enabled(userDto.isEnabled())
//                        .status(EUserStatus.valueOf(userDto.getStatus()))
//                        .build()
//                )
//        );
//    }
//
//    @Override
//    public UserDto update(UserDto userDto) throws DaoException.NoData {
//        log.debug("Request to update Customer : {}", userDto);
//        Optional<User> user = userRepository.findById(userDto.getId());
//        if (!user.isPresent()) {
//            throw new DaoException.NoData("User with id = " + userDto.getId() + " does not exist, so it cannot be modified.");
//        }
//        return mapToDto(userRepository.saveAndFlush(mapToUser(userDto, user.get())));
//    }
//
//    public List<UserDto> findAll() {
//        log.debug("Request to get all Customers");
//        return this.userRepository.findAll()
//                .stream()
//                .map(this::mapToDto)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional(readOnly = true)
//    public UserDto findById(Long id) {
//        log.debug("Request to get Customer : {}", id);
//        return this.userRepository.findById(id).map(this::mapToDto).orElse(null);
//    }
//
//    public List<UserDto> findAllActive() {
//        log.debug("Request to get all Customers");
//        return this.userRepository.findAllByEnabled(true)
//                .stream()
//                .map(this::mapToDto)
//                .collect(Collectors.toList());
//    }
//
//    public List<UserDto> findAllInactive() {
//        log.debug("Request to get all Customers");
//        return this.userRepository.findAllByEnabled(false)
//                .stream()
//                .map(this::mapToDto)
//                .collect(Collectors.toList());
//    }
//
//    public void delete(Long id) {
//        log.debug("Request to delete Customer : {}", id);
//
//        User user = this.userRepository.findById(id)
//                .orElseThrow(() -> new IllegalStateException("Cannot find Customer with id " + id));
//
//        user.setEnabled(false);
//        this.userRepository.save(user);
//    }
//
//    @Nullable
//    private UserDto mapToDto(User user) {
//        if (user != null) {
//            return UserDto.builder()
//                    .id(user.getId())
//                    .firstName(user.getFirstName())
//                    .lastName(user.getLastName())
//                    .email(user.getEmail())
//                    .phoneNumber(user.getPhoneNumber())
//                    .enabled(user.isEnabled())
//                    .build();
//        }
//        return null;
//    }
//
//    @Nullable
//    private User mapToUser(UserDto userDto, User user) {
//        if (userDto != null) {
//            return User.builder()
//                    .id(userDto.getId())
//                    .userName((userDto.getUserName()))
//                    .firstName(userDto.getFirstName())
//                    .lastName(userDto.getLastName())
//                    .email(userDto.getEmail() != null ? userDto.getEmail() : user.getEmail())
//                    .phoneNumber(user.getPhoneNumber())
//                    .password(user.getPassword())
//                    .build();
//        }
//        return null;
//    }
//
//}
