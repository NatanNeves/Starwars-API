package com.project.starwars.model.repositoriesTest;

import com.project.starwars.model.entities.Planet;
import com.project.starwars.model.repositories.PlanetRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PlanetRepositoryTest {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Buscar planeta por nome retorna um planeta")
    void findByName_success() {
        // Criando e persistindo um planeta
        String name = "Tatooine";
        Planet planet = createPlanet(name, "nevasca", "montanhoso");

        // Buscando o planeta pelo nome
        Optional<Planet> result = planetRepository.findByName(name);

        // Verificando se o planeta foi encontrado
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getName()).isEqualTo(name);
    }

    // Método para criar um planeta e persisti-lo no banco de dados
    private Planet createPlanet(String name, String climate, String terrain) {
        Planet planet = new Planet(name, climate, terrain);
        entityManager.persist(planet);
        entityManager.flush();
        return planet;
    }
}
