package com.fhkiel.oopproject.container;

import com.fhkiel.oopproject.model.Character;
import com.fhkiel.oopproject.model.UpdateVisitor;
import com.fhkiel.oopproject.serialize.Serializer;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Container for created characters with basic CRUD functionality
 * Implemented as singleton and Serializable to save the data on a hard drive
 */
public class CharacterContainer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static CharacterContainer instance = null;
    private ArrayList<Character> container;

    private CharacterContainer() {
        try {
            this.container = Serializer.readData().getAllCharacters();
        } catch (Exception e) {
            this.container = new ArrayList<>();
        }
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

    public void save() {
        Serializer.writeData(this);
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
        Character result = this.findById(c.getId());
        if (result != null)
            return result.acceptUpdater(new UpdateVisitor(), c);
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
