package com.ozgury.veterinerapp.service.impl;

import com.ozgury.veterinerapp.dto.Animal;
import com.ozgury.veterinerapp.exception.AnimalNotFoundException;
import com.ozgury.veterinerapp.exception.UserNotFoundException;
import com.ozgury.veterinerapp.repository.AnimalRepository;
import com.ozgury.veterinerapp.service.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements IAnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public void save(Animal animal) throws UserNotFoundException {
        if (Objects.isNull(animal.getPerson()))
            throw new UserNotFoundException("Hayvan kaydetmek için hayvan sahibi girmelisiniz!");
        animalRepository.save(animal);
    }

    @Override
    public List<Animal> listAll() {
        return animalRepository.findAll();
    }

    @Override
    public Animal findByAnimalId(Long id) throws AnimalNotFoundException {
        Optional<Animal> animal = animalRepository.findById(id);
        System.out.println(animal);
        if (animal.isPresent())
            return animal.get();
        else
            throw new AnimalNotFoundException("Could not find any animal with ID" + id);

    }

    @Override
    public void deleteFindByAnimal(Long id) throws UserNotFoundException {
        Optional<Animal> animal = animalRepository.findById(id);
        if (!animal.isPresent())
            throw new UserNotFoundException("Could not find any animals with ID" + id);

        animalRepository.deleteById(id);
    }

    @Override
    public Animal animalByName(String name) {
        return animalRepository.findByName(name);
    }
}
