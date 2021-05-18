package com.fhkiel.oopproject.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("StarWars")
public class StarWarsChar extends Character {
    private String spaceship;

    public StarWarsChar(String firstname, String lastname, int age, String spaceship) {
        super(firstname, lastname, age);
        this.setSpaceship(spaceship);
    }

    public StarWarsChar update(StarWarsChar c) {
        this.setFirstname(c.getFirstname()).setLastname(c.getLastname()).setAge(c.getAge());
        this.setSpaceship(c.getSpaceship());
        return this;
    }

    public String getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(String spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public Character accept(UpdateVisitor v, Character c) {
        return v.visit(this, c);
    }
}
