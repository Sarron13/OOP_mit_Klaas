package com.fhkiel.oopproject.container;

import com.fhkiel.oopproject.model.Character;
import com.fhkiel.oopproject.model.StarWarsChar;

import java.util.ArrayList;

public class ContainerMain {

    public static void main(String[] args) {
        CharacterContainer container = CharacterContainer.getInstance();
        StarWarsChar c = new StarWarsChar("Lukas", "Skywalker", 25, "X-Wing");
        container.addCharacter(c);
        ArrayList<Character> allChars = container.getAllCharacters();
        Character c2 = container.findByID(allChars.get(0).getId());
        // update c2
        // delete c2
    }

}
