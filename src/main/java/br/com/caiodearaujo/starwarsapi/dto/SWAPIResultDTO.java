package br.com.caiodearaujo.starwarsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SWAPIResultDTO {

    private Integer count = 0;
    private String next;
    private String previous;
    private List<SWAPIPLanetDTO> results = Collections.emptyList();

}
