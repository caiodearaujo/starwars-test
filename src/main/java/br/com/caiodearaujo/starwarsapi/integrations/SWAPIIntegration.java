package br.com.caiodearaujo.starwarsapi.integrations;

import br.com.caiodearaujo.starwarsapi.dto.SWAPIResultDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class SWAPIIntegration {

    @Value("${custom.swapi.url.planet.search}")
    private String swapiSearchURL;

    public Mono<SWAPIResultDTO> getAllPlanets(String name, Integer page) {
        return WebClient.create(String.format(swapiSearchURL, name, page))
                .get()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(SWAPIResultDTO.class);
    }

}
