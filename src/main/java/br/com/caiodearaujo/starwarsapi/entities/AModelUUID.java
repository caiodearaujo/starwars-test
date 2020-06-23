package br.com.caiodearaujo.starwarsapi.entities;

import lombok.Getter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.util.UUID;

public abstract class AModelUUID {

    @Getter
    @PrimaryKey
    private String id;

    public AModelUUID() {
        this.id = UUID.randomUUID().toString();
    }
}
