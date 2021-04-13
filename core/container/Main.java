package core.container;

import core.model.Character;

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

        //UPDATE
        grogu.setLastname("Yoda");
        container.updateCharacter(grogu);
        System.out.println(container);

        //READ
        List<Character> myList = container.getAllCharacters();
        for (Character c : myList) {
            System.out.println(c);
        }
        System.out.println(container.search("Z"));

        //DELETE
        container.deleteByCharacter(grogu);
        System.out.println(container);
    }
}
