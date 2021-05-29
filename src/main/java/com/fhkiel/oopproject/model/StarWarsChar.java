package com.fhkiel.oopproject.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * <strong>Class-Description:</strong><br/>
 * The class "StarWarsChar" is a concrete child class of the abstract
 * Character and represents Characters from the Star-Wars-Universe.
 */

@JsonTypeName("StarWars")
public class StarWarsChar extends Character {
    private String spaceship;

    /**
     * Creates an instance of a Star-Wars-Character by using the {@link Character#Character Constructor} of the
     * {@link Character Character Class}.
     * @param firstname Type: {@link String}
     * @param lastname Type: {@link String}
     * @param age Type: int
     * @param spaceship Type: {@link String}
     */
    public StarWarsChar(String firstname, String lastname, int age, String spaceship) {
        super(firstname, lastname, age);
        this.setSpaceship(spaceship);
    }

    /**
     * Updates the attributes of the current instance of {@link StarWarsChar} by using the temporary created updated
     * version of itself.
     * @param c Type: {@link StarWarsChar}
     * @return The updated instance of the {@link StarWarsChar Star-Wars-Character}
     */
    public StarWarsChar update(StarWarsChar c) {
        this.setFirstname(c.getFirstname()).setLastname(c.getLastname()).setAge(c.getAge());
        this.setSpaceship(c.getSpaceship());
        return this;
    }

    /**
     * @return The {@link #spaceship} of the current instance of {@link StarWarsChar} as {@link String}
     */
    public String getSpaceship() {
        return spaceship;
    }

    /**
     * @param spaceship Type: {@link String}
     */
    public void setSpaceship(String spaceship) {
        this.spaceship = spaceship;
    }

    /**
     * This method is used to perform the {@link #update update process} by using a {@link UpdateVisitor Visitor} and
     * automatically upcast to a {@link StarWarsChar}.
     * @param v Type: {@link UpdateVisitor}
     * @param c Type: {@link Character}
     * @return The updated instance of the {@link StarWarsChar}
     */
    @Override
    public Character acceptUpdater(UpdateVisitor v, Character c) {
        return v.visit(this, c);
    }
}
