package com.ernez.craftapp.service;

import com.ernez.craftapp.dto.UserDto;
import com.ernez.craftapp.exception.DaoException;

import java.util.List;

public interface UserService {
    public UserDto create(UserDto userDto);
    public UserDto update(UserDto userDto) throws DaoException.NoData;
    public List<UserDto> findAll();
    public UserDto findById(Long id);
    public List<UserDto> findAllActive();
    public List<UserDto> findAllInactive();
    public void delete(Long id);
}
