package com.ozgury.veterinerapp.controller;

import com.ozgury.veterinerapp.dto.Animal;
import com.ozgury.veterinerapp.dto.Person;
import com.ozgury.veterinerapp.exception.AnimalNotFoundException;
import com.ozgury.veterinerapp.exception.UserNotFoundException;
import com.ozgury.veterinerapp.service.IAnimalService;
import com.ozgury.veterinerapp.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AnimalController {

    @Autowired
    private IAnimalService animalService;
    @Autowired
    private IPersonService personService;

    @GetMapping("/animals")
    public String showPersonList(Model model) {
        List<Animal> listAnimals = animalService.listAll();
        model.addAttribute("listAnimals", listAnimals);
        return "animals";
    }

    @GetMapping("/animals/new")
    public String showNewForm(Model model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("pageTitle", "Hayvvan Ekle");

        List<Person> personList = personService.listAll();
        List<String> personName = personList.stream()
                .map(Person::getNameSurname)
                .collect(Collectors.toList());


        model.addAttribute("personName", personList);
        return "animal_form";
    }

    @PostMapping("/animals/save")
    public String saveAnimal(Animal animal, RedirectAttributes ra) {
        try {
            animalService.save(animal);
            ra.addFlashAttribute("message", "Başarıyla eklendi.");
            return "redirect:/persons";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/persons";
        }
    }

    @GetMapping("/animals/edit/{id}")
    public String getFindByAnimal(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Animal animal = animalService.findByAnimalId(id);
            List<Person> personList = personService.listAll();
            model.addAttribute("personName", personList);
            model.addAttribute("animal", animal);
            model.addAttribute("pageTitle", "Hayvan düzenle (Adı : " + animal.getName() + ")");
            return "animal_form";
        } catch (AnimalNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/persons";
        }
    }

    @GetMapping("/animal/{id}")
    public String findByAnimalPage(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Animal animal = animalService.findByAnimalId(id);
//            List<Animal> animals = animalService.getByPersonId(person.getPersonId());
//            model.addAttribute("animals",animals);
            model.addAttribute("animal", animal);
            model.addAttribute("pageTitle", "Kişiyi düzenle (Ad Soyad: " + animal.getName() + ")");
            return "animal";

        } catch (AnimalNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/persons";
        }
    }

    @GetMapping("/animals/delete/{id}")
    public String deleteFindByAnimal(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            animalService.deleteFindByAnimal(id);
            ra.addFlashAttribute("message", "Hayvan silindi.");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/animals";

    }

    @GetMapping("/animals/search/{name}")
    public void inquireAnimal(@PathVariable("name") String name) {
        Animal animal = animalService.animalByName(name);
    }
}
