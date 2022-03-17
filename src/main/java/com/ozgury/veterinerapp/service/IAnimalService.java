package com.ozgury.veterinerapp.service;

import com.ozgury.veterinerapp.dto.Animal;
import com.ozgury.veterinerapp.exception.AnimalNotFoundException;
import com.ozgury.veterinerapp.exception.UserNotFoundException;

import java.util.List;

public interface IAnimalService {
    void save(Animal animal) throws UserNotFoundException;

    List<Animal> listAll();

    Animal findByAnimalId(Long id) throws AnimalNotFoundException;

    void deleteFindByAnimal(Long id) throws UserNotFoundException;

    Animal animalByName(String name);
}
