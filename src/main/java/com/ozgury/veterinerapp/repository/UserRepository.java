package com.ozgury.veterinerapp.repository;

import com.ozgury.veterinerapp.dto.Person;
import com.ozgury.veterinerapp.dto.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String email);

}
