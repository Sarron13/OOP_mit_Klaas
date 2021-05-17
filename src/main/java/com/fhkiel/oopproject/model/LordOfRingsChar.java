package com.fhkiel.oopproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("LotR")
public class LordOfRingsChar extends Character {
    private String favoriteTobacco;

    public LordOfRingsChar(String firstname, String lastname, int age, String tobacco) {
        super(firstname, lastname, age);
        this.setFavoriteTobacco(tobacco);
    }

    public String getFavoriteTobacco() {
        return favoriteTobacco;
    }

    public void setFavoriteTobacco(String favoriteTobacco) {
        this.favoriteTobacco = favoriteTobacco;
    }
}
