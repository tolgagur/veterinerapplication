package com.ozgury.veterinerapp.service;

import com.ozgury.veterinerapp.dto.Person;
import com.ozgury.veterinerapp.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IPersonService {
    void save(Person person);

    List<Person> listAll();

    List<Person> listAllKeyword(String keyword);

    Person getFindByPerson(Long id) throws UserNotFoundException;

    void deleteFindByPerson(Long id) throws UserNotFoundException;

    Person personByName(String name);

    Optional<Person> findByPersonEmail(String email);
}
