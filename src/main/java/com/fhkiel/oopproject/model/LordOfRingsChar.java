package com.fhkiel.oopproject.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("LotR")
public class LordOfRingsChar extends Character {
    private String favoriteTobacco;

    public LordOfRingsChar(String firstname, String lastname, int age, String tobacco) {
        super(firstname, lastname, age);
        this.setFavoriteTobacco(tobacco);
    }

    public LordOfRingsChar update(LordOfRingsChar c){
        this.setFirstname(c.getFirstname()).setLastname(c.getLastname()).setAge(c.getAge());
        this.setFavoriteTobacco(c.getFavoriteTobacco());
        return this;
    }

    public String getFavoriteTobacco() {
        return favoriteTobacco;
    }

    public void setFavoriteTobacco(String favoriteTobacco) {
        this.favoriteTobacco = favoriteTobacco;
    }

    @Override
    public Character accept(UpdateVisitor v, Character c) {
        return v.visit(this, c);
    }
}
