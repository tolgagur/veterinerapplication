package com.ozgury.veterinerapp.repository;


import com.ozgury.veterinerapp.dto.Person;
import com.ozgury.veterinerapp.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class PersonRepositoryTests {
    @Autowired private PersonRepository personRepository;

    @Test
    @Order(1)
    public void testAddPersonNew(){
        Person person = new Person();
        person.setNameSurname("test test");
        person.setTelephone("905554443322");
        person.setEmail("test@test.com");
        person.setContactinformation("Açıklama");

        Person savedPerson = personRepository.save(person);

        Assertions.assertThat(savedPerson).isNotNull();
        Assertions.assertThat(savedPerson.getPersonId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void testListAll(){
        Iterable<Person> people = personRepository.findAll();
        Assertions.assertThat(people).hasSizeGreaterThan(0);
    }

    @Test
    @Order(3)
    public void testfindByName(){
        List<Person> personList = personRepository.findByName("tolga");
        Assertions.assertThat(personList.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    public void testfindByNameSurname(){
        List<Person> personList = personRepository.findAll();
        Assertions.assertThat(personList.size()).isGreaterThan(0);
    }

    @Test
    @Order(5)
    public void testfindByEmail(){
        Person person = personRepository.findByEmail("test@test.com");
        Assertions.assertThat(person.getPersonId()).isGreaterThan(0);
    }

    @Test
    @Order(6)
    public void testdeletePerson(){
        Person testPerson = personRepository.findByEmail("test@test.com");
        Person person = personRepository.findById(testPerson.getPersonId()).get();
        personRepository.delete(person);

        Person person1 = null;

        Optional<Person> optionalPerson = Optional.ofNullable(personRepository.findByEmail("test@test.com"));

        if(optionalPerson.isPresent()){
            person1 = optionalPerson.get();
        }

        Assertions.assertThat(person1).isNull();

    }



}
