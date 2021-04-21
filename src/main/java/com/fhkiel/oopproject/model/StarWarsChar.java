package com.fhkiel.oopproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("StarWars")
public class StarWarsChar extends Character {
    private String spaceship;

    public StarWarsChar(String firstname, String lastname, int age, String spaceship) {
        super(firstname, lastname, age);
        this.setSpaceship(spaceship);
    }

    public String getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(String spaceship) {
        this.spaceship = spaceship;
    }
}
