package com.fhkiel.oopproject.container;

import com.fhkiel.oopproject.model.Character;
import com.fhkiel.oopproject.container.CharacterContainer;
import com.fhkiel.oopproject.serialize.Serializer;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        CharacterContainer container = CharacterContainer.getInstance();

        // CREATE
        Character grogu = new Character("Grogu", "Grogu", 50);
        Character mando = new Character("Mando", "Grogu", 50);
        Character boba = new Character("Boba", "Fett", 50);
        container.addCharacter(grogu);
        container.addCharacter(boba);
        container.addCharacter(mando);
        System.out.println(container);
        //System.out.println("Save Container");
        //Serializer.writeData(container);
        System.out.println("Read Container");
        System.out.println(Serializer.readData());
        CharacterContainer newContainer = Serializer.readData();

        newContainer.search("Grogu");

        /*
         * //UPDATE grogu.setLastname("Yoda"); container.updateCharacter(grogu);
         * System.out.println(container);
         * 
         * //READ List<Character> myList = container.getAllCharacters(); for (Character
         * c : myList) { System.out.println(c); }
         * System.out.println(container.search("Z"));
         * 
         * //DELETE container.deleteByID(grogu.getId()); System.out.println(container);
         */
    }
}
