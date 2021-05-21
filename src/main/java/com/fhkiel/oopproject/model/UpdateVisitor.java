package com.fhkiel.oopproject.model;

/**
 * <strong>Class-Description:</strong></br>
 * The UpdateVisitor-Class automatically differentiate between the two
 * concrete classes and perfomes the updateprocess of these
 * classes.
 */
public class UpdateVisitor {

    public StarWarsChar visit(StarWarsChar c, Character newC) {
        return c.update((StarWarsChar) newC);
    }

    public LordOfRingsChar visit(LordOfRingsChar c, Character newC){
        return c.update((LordOfRingsChar) newC);
    }
}
