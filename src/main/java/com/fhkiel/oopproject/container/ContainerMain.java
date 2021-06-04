package com.fhkiel.oopproject.container;

import com.fhkiel.oopproject.model.Character;
import com.fhkiel.oopproject.model.LordOfRingsChar;
import com.fhkiel.oopproject.model.StarWarsChar;

import java.util.ArrayList;
import java.util.List;

public class ContainerMain {

    public static void main(String[] args) {
        CharacterContainer container = CharacterContainer.getInstance();

        // Add
        StarWarsChar c = new StarWarsChar("Lukas", "Skywalker", 25, "X-Wing");
        container.addCharacter(c);
        container.addCharacter(new LordOfRingsChar("Bilbo", "Beutlin", 56, "Lucky"));

        // Get all
        ArrayList<Character> allChars = container.getAllCharacters();

        // Find
        Character c2 = container.findByID(allChars.get(0).getId());

        // Search
        List<Character> cList = container.search("Skywalker");
        List<Character> cList2 = container.search("Vader");

        // update c2
        System.out.println("Old:\n" + c2 +"\n");
        StarWarsChar newC2 = new StarWarsChar("Luke", "Skywalker", 30, "X-Wing");
        System.out.println("Updated:\n" + container.updateCharacter((Character) newC2) + "\n");

        // delete c2
        System.out.println("Old list:\n" + container.toString() + "\n");
        container.deleteByID(c2.getId());
        System.out.println("New list:\n" + container.toString() + "\n");
    }

}
