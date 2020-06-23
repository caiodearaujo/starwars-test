package br.com.caiodearaujo.starwarsapi;

import br.com.caiodearaujo.starwarsapi.entities.Planet;
import br.com.caiodearaujo.starwarsapi.repositories.PlanetRepository;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StarWarsApiApplicationTests {

    @Autowired
    PlanetRepository planetRepository;

    @Test
    @Order(1)
    public void deleteAndInsert() {
        Flux<Planet> deleteAndInsert = planetRepository.deleteAll()
                .thenMany(planetRepository.saveAll(getFourFluxPlanets()));
        StepVerifier
                .create(deleteAndInsert)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    @Order(2)
    public void testWhenInsertedIncreaseCount() {
        Mono<Long> saveAndCount = planetRepository.count()
                .doOnNext(System.out::println)
                .thenMany(planetRepository.saveAll(getTwoFluxPlanets()))
                .last()
                .flatMap(p -> planetRepository.count())
                .doOnNext(System.out::println);

        StepVerifier
                .create(saveAndCount)
                .expectNext(6L)
                .verifyComplete();
    }

    @Test
    @Order(3)
    public void testFilterByName() {
        StepVerifier
                .create(planetRepository.getPlanetByName("Tatooine"))
                .expectNextCount(1)
                .verifyComplete();
    }

    private static Flux<Planet> getFourFluxPlanets() {
        return Flux.just(
                Planet.builder().name("Tatooine").climate("Arid").terrain("Desert").build(),
                Planet.builder().name("Dagobah").climate("Murky").terrain("swamp, jungles").build(),
                Planet.builder().name("Naboo").climate("temperate").terrain("grassy hills, swamps, forests, mountains").build(),
                Planet.builder().name("Kashyyyk").climate("tropical").terrain("jungle, forests, lakes, rivers").build()
        );
    }

    private Flux<Planet> getTwoFluxPlanets() {
        return Flux.just(
                Planet.builder().name("Alderaan").climate("temperate").terrain("grasslands, mountains").build(),
                Planet.builder().name("Kamino").climate("temperate").terrain("ocean").build());
    }

}
