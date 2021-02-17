package de.nterra.mavenSpringBootAngularH2.repository;

import de.nterra.mavenSpringBootAngularH2.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    List<Pizza> findByPublished(boolean published);

    List<Pizza> findByTitleContaining(String title);
}