package com.project.starwars.model.services;

import com.project.starwars.model.entities.Planet;
import com.project.starwars.model.repositories.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetService {

    static private PlanetRepository repository;

    public PlanetService(PlanetRepository repository) {
        this.repository = repository;
    }

    //buscar todos os planetas
    public List<Planet> planets() {
        return repository.findAll();
    }

    //criar um planeta
    public Planet save(Planet planet) {
        return repository.save(planet);
    }

    //procurar por id
    public Planet findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    // deletar por id
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
