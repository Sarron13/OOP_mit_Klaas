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

    public Character updateCharacter(Character c) {
        if (this.findById(c.getId()) != null)
            return this.findById(c.getId()).update(c);
        else
            return null;
    }

    public boolean deleteByID(UUID id) {
        for (int i = 0; i < container.size(); i++) {
            if (container.get(i).getId().equals(id))
                container.remove(i);
            return true;
        }
        return false;
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
