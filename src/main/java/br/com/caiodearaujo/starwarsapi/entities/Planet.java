package br.com.caiodearaujo.starwarsapi.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Planet extends AModelUUID {

    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private String climate;
    @NotEmpty
    @NotNull
    private String terrain;
    private Integer films;

}
