package com.ozgury.veterinerapp.service.impl;

import com.ozgury.veterinerapp.dto.Person;
import com.ozgury.veterinerapp.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PersonServiceImplTest {


    @MockBean
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    public void testSave()
    {
        Person person1 = new Person();
        person1.setNameSurname("test test");
        person1.setTelephone("905554443322");
        person1.setEmail("test@test.com");
        person1.setContactinformation("Açıklama");

        personService.save(person1);
        Mockito.verify(personService, Mockito.times(1)).save(eq(person1));
    }
    @Test
    void save() {
        List<Person> list = new ArrayList<Person>();
        Person person1 = new Person();
        person1.setNameSurname("test test");
        person1.setTelephone("905554443322");
        person1.setEmail("test@test.com");
        person1.setContactinformation("Açıklama");

        Person person2 = new Person();
        person1.setNameSurname("test2 test2");
        person1.setTelephone("905554443321");
        person1.setEmail("test2@test2.com");
        person1.setContactinformation("Açıklama");

        Person person3 = new Person();
        person1.setNameSurname("test3 test3");
        person1.setTelephone("905554443320");
        person1.setEmail("test3@test3.com");
        person1.setContactinformation("Açıklama");

        personService.save(person1);
        personService.save(person2);
        personService.save(person3);
        list.add(person1);
        list.add(person2);
        list.add(person3);

        when(personService.listAll()).thenReturn(list);

        List<Person> personList = personService.listAll();

        assertEquals(3, personList.size());
        verify(personService, times(1)).listAll();
    }

    @Test
    void listAll() {
    }

    @Test
    void listAllKeyword() {
    }

    @Test
    void getFindByPerson() {
    }

    @Test
    void deleteFindByPerson() {
    }

    @Test
    void personByName() {
    }

    @Test
    void findByPersonEmail() {
    }
}