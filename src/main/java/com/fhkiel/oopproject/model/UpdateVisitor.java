package com.fhkiel.oopproject.model;

public class UpdateVisitor {
    public StarWarsChar visit(StarWarsChar c, Character newC) {
        return c.update((StarWarsChar) newC);
    }
    public LordOfRingsChar visit(LordOfRingsChar c, Character newC){
        return c.update((LordOfRingsChar) newC);
    }
}
