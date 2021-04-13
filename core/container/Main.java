package core.container;

import core.model.Character;

public class Main {


    public static void main(String[] args) {
        CharacterContainer container = CharacterContainer.getInstance();
        Character grogu = new Character("Grogu", "Grogu", 50);
        Character mando = new Character("Mando", "Grogu", 50);
        Character boba = new Character("Boba", "Fett", 50);
        container.addCharacter(grogu);
        container.addCharacter(boba);
        container.addCharacter(mando);

        System.out.println(container);

    }
}
