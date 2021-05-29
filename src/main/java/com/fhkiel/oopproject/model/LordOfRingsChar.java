package com.fhkiel.oopproject.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

//TODO: Is Reload after editing a Character ok?

/**
 * <strong>Class-Description:</strong><br/>
 * The class "LordOfRingsChar" is a concrete child class of the abstract
 * Character and represents Characters from the LotR-Universe.
 */
@JsonTypeName("LotR")
public class LordOfRingsChar extends Character {
    private String favoriteTobacco;

    /**
     * Creates an instance of a Lord-of-the-Rings-Character by using the {@link Character#Character Constructor} of the
     * {@link Character Character Class}.
     * @param firstname Type: {@link String}
     * @param lastname Type: {@link String}
     * @param age Type: int
     * @param tobacco Type: {@link String}
     */
    public LordOfRingsChar(String firstname, String lastname, int age, String tobacco) {
        super(firstname, lastname, age);
        this.setFavoriteTobacco(tobacco);
    }

    /**
     * Updates the attributes of the current instance of {@link LordOfRingsChar} by using the temporary created updated
     * version of itself.
     * @param c Type: {@link LordOfRingsChar}
     * @return The updated instance of the {@link LordOfRingsChar Lord-of-the-Rings-Character}
     */
    public LordOfRingsChar update(LordOfRingsChar c){
        this.setFirstname(c.getFirstname()).setLastname(c.getLastname()).setAge(c.getAge());
        this.setFavoriteTobacco(c.getFavoriteTobacco());
        return this;
    }

    /**
     * @return The {@link #favoriteTobacco favorite tobacco} of the current instance of {@link LordOfRingsChar} as {@link String}
     */
    public String getFavoriteTobacco() {
        return favoriteTobacco;
    }

    /**
     * @param favoriteTobacco Type: {@link String}
     */
    public void setFavoriteTobacco(String favoriteTobacco) {
        this.favoriteTobacco = favoriteTobacco;
    }

    /**
     * This method is used to perform the {@link #update update process} by using a {@link UpdateVisitor Visitor} and
     * automatically upcast to a {@link LordOfRingsChar}.
     * @param v Type: {@link UpdateVisitor}
     * @param c Type: {@link Character}
     * @return The updated instance of the {@link LordOfRingsChar}
     */
    @Override
    public Character acceptUpdater(UpdateVisitor v, Character c) {
        return v.visit(this, c);
    }
}
