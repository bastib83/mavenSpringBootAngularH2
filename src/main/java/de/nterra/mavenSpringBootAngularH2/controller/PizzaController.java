package de.nterra.mavenSpringBootAngularH2.controller;

import de.nterra.mavenSpringBootAngularH2.model.Pizza;
import de.nterra.mavenSpringBootAngularH2.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PizzaController {

    @Autowired
    PizzaRepository pizzaRepository;

    @GetMapping("/pizzas")
    public ResponseEntity<List<Pizza>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<Pizza> pizzas = new ArrayList<Pizza>();

            if (title == null)
                pizzaRepository.findAll().forEach(pizzas::add);
            else
                pizzaRepository.findByTitleContaining(title).forEach(pizzas::add);

            if (pizzas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pizzas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pizzas/{id}")
    public ResponseEntity<Pizza> getTutorialById(@PathVariable("id") long id) {
        Optional<Pizza> tutorialData = pizzaRepository.findById(id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/pizzas")
    public ResponseEntity<Pizza> createTutorial(@RequestBody Pizza tutorial) {
        try {
            Pizza _tutorial = pizzaRepository
                    .save(new Pizza(tutorial.getTitle(), tutorial.getDescription(), tutorial.getPrice(), false));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pizzas/{id}")
    public ResponseEntity<Pizza> updateTutorial(@PathVariable("id") long id, @RequestBody Pizza tutorial) {
        Optional<Pizza> tutorialData = pizzaRepository.findById(id);

        if (tutorialData.isPresent()) {
            Pizza _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(pizzaRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/pizzas/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            pizzaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/pizzas")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            pizzaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/pizzas/published")
    public ResponseEntity<List<Pizza>> findByPublished() {
        try {
            List<Pizza> tutorials = pizzaRepository.findByPublished(true);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}