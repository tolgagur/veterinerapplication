package com.ozgury.veterinerapp.service.impl;


import com.ozgury.veterinerapp.dto.Person;
import com.ozgury.veterinerapp.exception.UserNotFoundException;
import com.ozgury.veterinerapp.repository.PersonRepository;
import com.ozgury.veterinerapp.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public List<Person> listAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> listAllKeyword(String keyword) {
        if (keyword != null)
            return personRepository.findByName(keyword.toLowerCase());

        return personRepository.findAll();
    }

    @Override
    public Person getFindByPerson(Long id) throws UserNotFoundException {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent())
            return person.get();
        else
            throw new UserNotFoundException("Could not find any persons with ID" + id);
    }

    @Override
    public void deleteFindByPerson(Long id) throws UserNotFoundException {
        Optional<Person> person = personRepository.findById(id);
        if (!person.isPresent())
            throw new UserNotFoundException("Could not find any persons with ID" + id);

        personRepository.deleteById(id);
    }

    @Override
    public Person personByName(String name) {
        Person person = personRepository.findByNameSurname(name);
        return person;
    }

    @Override
    public Optional<Person> findByPersonEmail(String email) {
        return Optional.ofNullable(personRepository.findByEmail(email));
    }
}
