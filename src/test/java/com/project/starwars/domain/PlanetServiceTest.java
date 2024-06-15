package com.project.starwars.domain;

import com.project.starwars.domain.entities.Planet;
import com.project.starwars.domain.repositories.PlanetRepository;
import com.project.starwars.domain.services.PlanetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.project.starwars.common.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @InjectMocks
    private PlanetService planetService;

    @Mock
    private PlanetRepository planetRepository;



    @Test
    @DisplayName("criação de planeta com dados validos retorna planeta criado")
    public void createPlanet_case1(){

        //Arrange
        when(planetRepository.save(PLANET)).thenReturn(PLANET);

        //act
        Planet sut = planetService.create(PLANET);

        //assert
        assertThat(sut).isEqualTo(PLANET);
    }

    @Test
    @DisplayName("buscar planeta por id retorna planeta encontrado")
    public void findById_case1(){

        // Arrange
        Integer id =1;
        when(planetRepository.findById(id)).thenReturn(Optional.of(PLANET));

        // Act
        Optional<Planet> sut = planetService.findById(id);

        // Assert
        assertThat(sut).isPresent(); // Verifica se o Optional não está vazio
        assertThat(sut.get()).isEqualTo(PLANET); // Compara o objeto Planet retornado com o objeto esperado
    }

    @Test
    @DisplayName("buscar planeta por id inexistente retorna não encontrado")
    public void findById_case2(){

        // Arrange
        Integer id = 999; // ID que não existe
        when(planetRepository.findById(id)).thenReturn(Optional.empty()); // Configura o mock para retornar Optional vazio

        // Act
        Optional<Planet> sut = planetService.findById(id);

        // Assert
        assertThat(sut).isNotPresent(); // Verifica se o Optional está vazio
    }

    @Test
    @DisplayName("buscar planeta por nome valido")
    public void findByName_case1(){

        // Arrange
        when(planetRepository.findByName(PLANET.getName())).thenReturn(Optional.of(PLANET));

        // Act
        Optional<Planet> sut = planetService.findByName(PLANET.getName());

        // Assert
        assertThat(sut).isPresent().containsSame(PLANET);
    }
}
