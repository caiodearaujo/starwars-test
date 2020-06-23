package br.com.caiodearaujo.starwarsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SWAPIPLanetDTO {

    private String name;
    @JsonProperty(value = "rotation_period")
    private String rotationPeriod;
    @JsonProperty(value = "orbital_period")
    private String orbitalPeriod;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;
    @JsonProperty(value = "surface_water")
    private String surfaceWater;
    private String population;
    ArrayList<String> residents;
    ArrayList<String> films;
    private String created;
    private String edited;
    private String url;


}
