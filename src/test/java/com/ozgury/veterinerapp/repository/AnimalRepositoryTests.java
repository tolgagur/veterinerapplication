package com.ozgury.veterinerapp.repository;


import com.ozgury.veterinerapp.dto.Animal;
import com.ozgury.veterinerapp.dto.Person;
import com.ozgury.veterinerapp.repository.AnimalRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AnimalRepositoryTests {
    @Autowired
    private AnimalRepository animalRepository;

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

        Animal animal = new Animal();
        animal.setName("test name");
        animal.setPerson(savedPerson);
        animal.setSpecies("test species");
        animal.setGenus("test genus");
        animal.setAge("1");
        animal.setDate(LocalDateTime.now());
        animal.setDescription("aciklama");
        Animal savedAnimal = animalRepository.save(animal);

        Assertions.assertThat(savedAnimal).isNotNull();
        Assertions.assertThat(savedAnimal.getId()).isGreaterThan(0);

    }

    @Test
    @Order(6)
    public void testdeleteAnimal(){


        Animal testAnimal = animalRepository.findById(1L).get();
        animalRepository.delete(testAnimal);

        Animal animal = null;

        Optional<Animal> optionalAnimal = animalRepository.findById(testAnimal.getId());

        if(optionalAnimal.isPresent()){
            animal = optionalAnimal.get();
        }

        Assertions.assertThat(animal).isNull();

    }

    @Test
    public void testListAll(){
        Iterable<Animal> animal = animalRepository.findAll();
        Assertions.assertThat(animal).hasSizeGreaterThan(0);
    }


}
