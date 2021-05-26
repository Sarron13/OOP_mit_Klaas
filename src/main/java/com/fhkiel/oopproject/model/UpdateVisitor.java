package com.fhkiel.oopproject.model;

/**
 * <strong>Class-Description:</strong><br/>
 * The UpdateVisitor-Class automatically differentiate between the two
 * concrete classes and perfomes the updateprocess of these
 * classes.
 */
public class UpdateVisitor {

    /**
     * @param c Type: {@link StarWarsChar}
     * @param newC Type: {@link Character}
     * @return The updated version of the StarWars-Character
     */
    public StarWarsChar visit(StarWarsChar c, Character newC) {
        return c.update((StarWarsChar) newC);
    }

    /**
     * @param c Type: {@link LordOfRingsChar}
     * @param newC Type: {@link Character}
     * @return The updated version of the LordOfRings-Character
     */
    public LordOfRingsChar visit(LordOfRingsChar c, Character newC){
        return c.update((LordOfRingsChar) newC);
    }
}
