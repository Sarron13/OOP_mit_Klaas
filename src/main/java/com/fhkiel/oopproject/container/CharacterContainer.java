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
 * <strong>Class-Description:</strong><br/>
 * Container for created {@link Character characters} with basic CRUD functionality
 * Implemented as singleton and {@link Serializable} to save the data on a hard drive.
 */
public class CharacterContainer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static CharacterContainer instance = null;
    private ArrayList<Character> container;

    /**
     * Creates an {@link CharacterContainer} Object and loads the persistent data from the file mentioned in the {@link Serializer Serializer Class}.
     * If this file doesn't exists, the {@link Serializer Serializer Class} will create the file and the {@link CharacterContainer} will have an empty list.
     */
    private CharacterContainer() {
        try {
            this.container = Serializer.readData().getAllCharacters();
        } catch (Exception e) {
            this.container = new ArrayList<>();
        }
    }

    /**
     * @return The only instance of the {@link CharacterContainer}
     */
    public static CharacterContainer getInstance() {
        if (instance == null) {
            instance = new CharacterContainer();
        }
        return instance;
    }

    /**
     * @return {@link ArrayList} of {@link Character Characters} (ArrayList could be empty)
     */
    public ArrayList<Character> getAllCharacters() {
        return container;
    }

    /**
     * Saves the Current state of all {@link Character Characters} in {@link CharacterContainer}.
     */
    public void save() {
        Serializer.writeData(this);
    }

    /**
     * Adds a {@link Character} to the list "{@link #container}".
     * @param character Type: {@link Character}
     * @return A reference of the {@link CharacterContainer} Instance
     */
    public final CharacterContainer addCharacter(Character character) {
        this.container.add(character);
        return this;
    }

    /**
     * Searches in the {@link CharacterContainer} for every {@link Character}, who is matching to the {@link String}.
     * @param searchString Type: {@link String}
     * @return {@link List} of matching {@link Character Characters} (eventually a empty list)
     */
    public List<Character> search(String searchString) {
        List<Character> matchedCharacters = new ArrayList<>();
        for (Character c : this.getAllCharacters()) {
            if (c.matches(searchString))
                matchedCharacters.add(c);
        }
        return matchedCharacters;
    }

    /**
     * Overwrites a specific {@link Character} with its updated version.
     * @param c Type: {@link Character}
     * @return The updated {@link Character} if update was successful and "null" if {@link CharacterContainer} does not contain the {@link Character}
     */
    public Character updateCharacter(Character c) {
        Character result = this.findById(c.getId());
        if (result != null)
            return result.acceptUpdater(new UpdateVisitor(), c);
        else
            return null;
    }

    /**
     * Deletes a {@link Character} from the {@link CharacterContainer} by its {@link com.fhkiel.oopproject.model.Character#id ID}.
     * @param id Type: {@link UUID}
     * @return "true" when deleting successfull and "false" if not ({@link CharacterContainer} does not contain the {@link Character})
     */
    public boolean deleteByID(UUID id) {
        for (int i = 0; i < container.size(); i++) {
            if (container.get(i).getId().equals(id)) {
                container.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a Character in the {@link CharacterContainer} by its {@link com.fhkiel.oopproject.model.Character#id ID}.
     * @param id Type: {@link UUID}
     * @return The {@link Character} if {@link CharacterContainer} contains the {@link Character} and "null" if not
     */
    public Character findById(UUID id) {
        for (Character character : container) {
            if (character.getId().equals(id))
                return character;
        }
        return null;
    }

    /**
     * @return All {@link Character Characters} from the {@link CharacterContainer} as one formatted String
     */
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
