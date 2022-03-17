package com.ozgury.veterinerapp.controller;

import com.ozgury.veterinerapp.dto.Person;
import com.ozgury.veterinerapp.exception.UserNotFoundException;
import com.ozgury.veterinerapp.service.impl.AnimalServiceImpl;
import com.ozgury.veterinerapp.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

    @GetMapping("/persons")
    public String showPersonList(Model model, @Param("keyword") String keyword) {
        List<Person> listPersons = personService.listAllKeyword(keyword);
        model.addAttribute("listPersons", listPersons);
        return "persons";
    }

    @GetMapping("/persons/new")
    public String showNewForm(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("pageTitle", "Kişi ekle");
        return "person_form";
    }

    @PostMapping("/persons/save")
    public String savePerson(Person person, RedirectAttributes ra) {
        Optional<Person> checkPerson = personService.findByPersonEmail(person.getEmail());
        if (checkPerson.isPresent()) {
            if (person.getPersonId()!=null){
                personService.save(person);
                ra.addFlashAttribute("message", "Kişi başarıyla güncellendi.");
                return "redirect:/persons";
            }
            ra.addFlashAttribute("message", "Mail kullanımda.");
            return "redirect:/persons/new";

        }

        personService.save(person);
        ra.addFlashAttribute("message", "Kişi başarıyla eklendi.");
        return "redirect:/persons";
    }

    @GetMapping("/persons/edit/{id}")
    public String getFindByPerson(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Person person = personService.getFindByPerson(id);
            model.addAttribute("person", person);
            model.addAttribute("pageTitle", "Kişiyi düzenle (Ad Soyad: " + person.getNameSurname() + ")");
            return "person_form";

        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/persons";
        }
    }

    @GetMapping("/person/{id}")
    public String getFindByPersonPage(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Person person = personService.getFindByPerson(id);
            System.out.println(person.getAnimals());
//            List<Animal> animals = animalService.getByPersonId(person.getPersonId());
//            model.addAttribute("animals",animals);
            model.addAttribute("person", person);
            model.addAttribute("pageTitle", "Kişiyi düzenle (Ad Soyad: " + person.getNameSurname() + ")");
            return "person";

        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/persons";
        }
    }

    @GetMapping("/persons/delete/{id}")
    public String deleteFindByPerson(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            personService.deleteFindByPerson(id);
            ra.addFlashAttribute("message", "Kişi silindi.");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/persons";
    }
}
