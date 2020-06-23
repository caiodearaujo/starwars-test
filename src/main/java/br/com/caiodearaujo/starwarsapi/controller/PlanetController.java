package br.com.caiodearaujo.starwarsapi.controller;

import br.com.caiodearaujo.starwarsapi.entities.Planet;
import br.com.caiodearaujo.starwarsapi.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("planets")
public class PlanetController {

    private final PlanetService service;

    @Autowired
    public PlanetController(PlanetService service) {
        this.service = service;
    }

    /**
     * Save a new StarWars planet
     *
     * @param planet
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Planet> savePlanet(@Valid @RequestBody Planet planet) {
        return this.service.save(planet);
    }

    /**
     * List all planets already saved
     *
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Planet> getAllPlanets() {
        return this.service.getAll();
    }

    /**
     * Get a planet by UUID
     *
     * @param uuid
     * @return
     */
    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Planet> getPlanetsById(@PathVariable(value = "uuid") String uuid) {
        return this.service.getById(uuid);
    }

    /**
     * Finds a StarWars planet by name that alread been saved
     *
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Planet> getAllPlanetsByName(@PathVariable(value = "name") String name) {
        return this.service.getAllByName(name);
    }

    /**
     * Delete a StarWars from database by UUID
     *
     * @param uuid
     * @return
     */
    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public Mono deletePlanet(@PathVariable(value = "uuid") String uuid) {
        return this.service.delete(uuid);
    }

}
