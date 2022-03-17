package com.ozgury.veterinerapp.repository;

import com.ozgury.veterinerapp.dto.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long> {

    Animal findByName(String name);
}
