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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PizzaController {

    @Autowired
    PizzaRepository pizzaRepository;

    @GetMapping("/pizzas")
    public ResponseEntity<List<Pizza>> getAllPizzas(@RequestParam(required = false) String title) {
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
    public ResponseEntity<Pizza> getPizzaById(@PathVariable("id") long id) {
        Optional<Pizza> pizzaData = pizzaRepository.findById(id);

        if (pizzaData.isPresent()) {
            return new ResponseEntity<>(pizzaData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/pizzas")
    public ResponseEntity<Pizza> createPizza(@RequestBody Pizza pizza) {
        try {
            Pizza _pizza = pizzaRepository
                    .save(new Pizza(pizza.getTitle(), pizza.getDescription(), pizza.getPrice(), false));
            return new ResponseEntity<>(_pizza, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pizzas/{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable("id") long id, @RequestBody Pizza pizza) {
        Optional<Pizza> pizzaData = pizzaRepository.findById(id);

        if (pizzaData.isPresent()) {
            Pizza _pizza = pizzaData.get();
            _pizza.setTitle(pizza.getTitle());
            _pizza.setDescription(pizza.getDescription());
            _pizza.setPublished(pizza.isPublished());
            return new ResponseEntity<>(pizzaRepository.save(_pizza), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/pizzas/{id}")
    public ResponseEntity<HttpStatus> deletePizza(@PathVariable("id") long id) {
        try {
            pizzaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/pizzas")
    public ResponseEntity<HttpStatus> deleteAllPizzas() {
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
            List<Pizza> pizzas = pizzaRepository.findByPublished(true);

            if (pizzas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pizzas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}