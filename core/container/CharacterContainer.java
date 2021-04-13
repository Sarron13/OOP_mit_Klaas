package core.container;

import core.model.Character;
import core.serialize.Serializer;

import java.util.ArrayList;
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

    public ArrayList<Character> getContainer() {
        return container;
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }

    public final CharacterContainer addCharacter(Character character) {
        this.container.add(character);
        return this;
    }

    public final CharacterContainer updateCharacter(Character character) {
        return this;
    }

    public final CharacterContainer deleteCharacter(Character deleteChar) {
        for (int i = 0; i < container.size(); i++) {
            if (container.get(i).getId().equals(deleteChar.getId()))
                container.remove(i);
        }
        return this;
    }

    public final Character findById(UUID id) {
        for (Character character : container) {
            if (character.getId().equals(id))
                return character;
        }
        return null;
    }

    @Override
    public String toString() {
        String output = "";
        for(Character c: this.getContainer()){
            output += c + "\n";
        }
        return output;
    }
}
