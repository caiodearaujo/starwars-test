package br.com.caiodearaujo.starwarsapi.repositories;

import br.com.caiodearaujo.starwarsapi.entities.Planet;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PlanetRepository extends ReactiveCassandraRepository<Planet, String> {

    @AllowFiltering
    Mono<Planet> getPlanetByName(String name);

}
