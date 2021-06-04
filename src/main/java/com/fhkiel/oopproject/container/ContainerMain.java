package com.fhkiel.oopproject.container;

import com.fhkiel.oopproject.model.Character;
import com.fhkiel.oopproject.model.LordOfRingsChar;
import com.fhkiel.oopproject.model.StarWarsChar;

import java.util.ArrayList;

public class ContainerMain {

    public static void main(String[] args) {
        CharacterContainer container = CharacterContainer.getInstance();
        System.out.println("Demo Programm\n");

        // Add
        System.out.println("Add Lukas Skywalker");
        StarWarsChar c = new StarWarsChar("Lukas", "Skywalker", 25, "X-Wing");
        container.addCharacter(c);
        System.out.println("Add Bilbo Beutlin");
        container.addCharacter(new LordOfRingsChar("Bilbo", "Beutlin", 56, "Lucky"));

        // Get all
        System.out.println("Get all chars: ");
        ArrayList<Character> allChars = container.getAllCharacters();
        System.out.println(allChars.toString() + "\n");

        // Find
        LordOfRingsChar c2 = (LordOfRingsChar) container.findByID(container.search("Bilbo").get(0).getId());
        System.out.println("Find Bilbo Beutlins ID and get Bilbo");
        System.out.println(c2 + "\n");
        System.out.println("change Bilbos Age and update");
        c2.setAge(102);
        System.out.println("Updated Bilbo:\n" + container.updateCharacter(c2));
        System.out.println("Updated List:\n" + container + "\n");

        // delete c2
        System.out.println("Delete Lukas Skywalker");
        container.deleteByID(c.getId());
        System.out.println("New list without Skywalker:\n" + container + "\n");
    }

}
