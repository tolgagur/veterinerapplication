package com.ozgury.veterinerapp.service.impl;

import com.ozgury.veterinerapp.dto.Person;
import com.ozgury.veterinerapp.dto.user.User;
import com.ozgury.veterinerapp.repository.UserRepository;
import com.ozgury.veterinerapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUserEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
}
