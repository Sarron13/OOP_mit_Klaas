package com.fhkiel.oopproject.container;

import com.fhkiel.oopproject.model.Character;
import com.fhkiel.oopproject.serialize.Serializer;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CharacterContainer implements Serializable {

    // The serialVersionUID is important for version control
    @Serial
    private static final long serialVersionUID = 1L;

    private static CharacterContainer instance = null;
    private final ArrayList<Character> container;

    private CharacterContainer() {
        this.container = new ArrayList<>();
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
        List<Character> matchedCharacters = new ArrayList<>();
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
            if (container.get(i).getId().equals(id)) {
                container.remove(i);
                return true;
            }
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Character c : this.getAllCharacters()) {
            sb.append(c);
            sb.append("\n");
        }
        return sb.toString();
    }
}
