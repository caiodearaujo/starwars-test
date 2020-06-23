package br.com.caiodearaujo.starwarsapi.services;

import br.com.caiodearaujo.starwarsapi.dto.SWAPIResultDTO;
import br.com.caiodearaujo.starwarsapi.entities.Planet;
import br.com.caiodearaujo.starwarsapi.integrations.SWAPIIntegration;
import br.com.caiodearaujo.starwarsapi.repositories.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlanetService {

    private final PlanetRepository repository;
    private final SWAPIIntegration integration;

    @Autowired
    public PlanetService(PlanetRepository repository,
                         SWAPIIntegration integration) {
        this.repository = repository;
        this.integration = integration;
    }

    public Mono<Planet> getById(String uuid) {
        return this.repository.findById(uuid);
    }

    public Mono<Planet> save(Planet planet) {
        return getByName(planet.getName()).flatMap(Mono::just)
                .switchIfEmpty(getAllPlanetsFromSWAPI(planet.getName(), 1)
                        .doOnSuccess(swapiResultDTO -> {
                            swapiResultDTO.getResults().stream().findFirst()
                                    .ifPresent(dto -> planet.setFilms(dto.getFilms().size()));
                        }).then(this.repository.save(planet))
                );
    }

    public Flux<Planet> getAll() {
        return this.repository.findAll();
    }

    public Flux<Planet> getAllByName(String name) {
        return this.repository.findAll()
                .filter(planet -> planet.getName().toLowerCase().contains(name.toLowerCase()));
    }

    public Mono<Planet> getByName(String name) {
        return this.repository.getPlanetByName(name);
    }

    public Mono<SWAPIResultDTO> getAllPlanetsFromSWAPI(String name, Integer page) {
        return this.integration.getAllPlanets(name, page);
    }

    public Mono<Void> delete(String uuid) {
        return this.repository.deleteById(uuid);
    }
}
