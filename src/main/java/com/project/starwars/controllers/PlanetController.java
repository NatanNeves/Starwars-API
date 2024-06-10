package com.project.starwars.controllers;

import java.util.List;
import com.project.starwars.model.entities.Planet;
import com.project.starwars.model.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @GetMapping
    public ResponseEntity<List<Planet>> getPlanets() {
        return ResponseEntity.ok(planetService.planets());
    }

    @PostMapping
    public ResponseEntity<Planet> createPlanet(@RequestBody Planet planet) {
        return ResponseEntity.ok(planetService.save(planet));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> getPlanet(@PathVariable Integer id) {
        return ResponseEntity.ok(planetService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanet(@PathVariable Integer id) {
        planetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

