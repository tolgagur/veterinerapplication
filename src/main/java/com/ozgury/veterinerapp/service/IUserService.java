package com.ozgury.veterinerapp.service;

import com.ozgury.veterinerapp.dto.Person;
import com.ozgury.veterinerapp.dto.user.User;

import java.util.Optional;

public interface IUserService {

    void save(User user);

    Optional<User> findByUserEmail(String email);

}
