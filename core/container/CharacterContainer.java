package core.container;

import core.model.Character;
import core.serialize.Serializer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CharacterContainer {

    private static CharacterContainer instance = null;
    private final ArrayList<Character> container;
    private Serializer serializer;


    private CharacterContainer() {
        this.container = new ArrayList<Character>();
    }

    public static CharacterContainer getInstance() {
        if (instance == null) {
            instance = new CharacterContainer();
        }
        return instance;
    }

    public ArrayList<Character> getAllCharacters() {
        return container;
    }

    public final CharacterContainer addCharacter(Character character) {
        this.container.add(character);
        return this;
    }

    public List<Character> search(String searchString) {
        List<Character> matchedCharacters = new ArrayList<Character>();
        for (Character c : this.getAllCharacters()) {
            if (c.matches(searchString))
                matchedCharacters.add(c);
        }
        return matchedCharacters;
    }

    public final CharacterContainer updateCharacter(Character c) {
        if (this.findById(c.getId()) != null) {
            this.findById(c.getId()).update(c);
        }
        return this;
    }

    public final CharacterContainer deleteByCharacter(Character c) {
        for (int i = 0; i < container.size(); i++) {
            if (container.get(i).equals(c))
                container.remove(i);
        }
        return this;
    }

    public Character findById(UUID id) {
        for (Character character : container) {
            if (character.getId().equals(id))
                return character;
        }
        return null;
    }

    public void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public String toString() {
        String output = "";
        for (Character c : this.getAllCharacters()) {
            output += c + "\n";
        }
        return output;
    }
}
