package com.ozgury.veterinerapp.repository;

import com.ozgury.veterinerapp.dto.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("Select distinct p from Person p left join Animal a on p.personId = a.person.personId where lower(p.nameSurname) like %:keyword% or lower(a.name) like %:keyword%")
    List<Person> findByName(String keyword);

    Person findByNameSurname(String name);
    Person findByEmail(String email);
}
