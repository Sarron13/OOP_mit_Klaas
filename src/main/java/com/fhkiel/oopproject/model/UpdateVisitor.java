package com.fhkiel.oopproject.model;

/**
 * <strong>Class-Description:</strong>
 * <p>
 * The UpdateVisitor-Class perfomes the updateprocess of the concrete
 * classes. For that the class automatically differentiate between the two
 * concrete classes.
 * </p>
 */
public class UpdateVisitor {

    public StarWarsChar visit(StarWarsChar c, Character newC) {
        return c.update((StarWarsChar) newC);
    }

    public LordOfRingsChar visit(LordOfRingsChar c, Character newC){
        return c.update((LordOfRingsChar) newC);
    }
}
