package br.com.caiodearaujo.starwarsapi.controller;

import br.com.caiodearaujo.starwarsapi.dto.SWAPIResultDTO;
import br.com.caiodearaujo.starwarsapi.integrations.SWAPIIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("swapi")
public class SWAPIController {

    private final SWAPIIntegration integration;

    @Autowired
    public SWAPIController(SWAPIIntegration integration) {
        this.integration = integration;
    }

    /**
     * Returns a mirror data from https://swapi.dev/
     *
     * @param page
     * @param name
     * @return
     */
    @GetMapping
    public Mono<SWAPIResultDTO> getAllPlanetsFromSWAPI(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "name", required = false, defaultValue = "") String name
    ) {
        return this.integration.getAllPlanets(name, page);
    }

}
