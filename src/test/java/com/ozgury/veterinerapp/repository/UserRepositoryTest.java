package com.ozgury.veterinerapp.repository;

import com.ozgury.veterinerapp.dto.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testUserSave() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("12345");
        user.setRole("ROLE_USER");

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getUserId()).isGreaterThan(0);
    }

    @Test
    void testfindByEmail() {
        User getUser = userRepository.findByEmail("test@test.com");

        Assertions.assertThat(getUser).isNotNull();
        Assertions.assertThat(getUser.getUserId()).isGreaterThan(0);
    }
}