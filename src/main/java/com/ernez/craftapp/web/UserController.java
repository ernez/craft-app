/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ernez.craftapp.web;

import com.ernez.craftapp.dto.UserDto;
import com.ernez.craftapp.exception.DaoException;
import com.ernez.craftapp.service.UserService;
import com.ernez.craftapp.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ernez.craftapp.web.utils.Web.API;

/**
 * @author n.lamouchi
 */
@RequiredArgsConstructor
@RestController
//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(API + "/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/active")
    public List<UserDto> findAllActiveUsers() {

        return this.userService.findAllActive();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto userDto) {
        try {
            return this.userService.update(userDto);
        } catch (DaoException.NoData noData) {
            noData.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.userService.delete(id);
    }
}
