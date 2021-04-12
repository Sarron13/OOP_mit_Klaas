package core.container;

import core.model.Character;

import java.util.ArrayList;

public class CharacterContainer {

    private static CharacterContainer instance = null;
    private ArrayList<Character> container;

    private CharacterContainer() {
        this.container = new ArrayList<Character>();
    }

    public static CharacterContainer getInstance() {
        if (instance == null) {
            instance = new CharacterContainer();
        }
        return instance;
    }

    public final CharacterContainer addCharacter(Character character) {
        this.container.add(character);
        return this;
    }
}
