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
 * Container for created characters with basic CRUD functionality
 * Implemented as singleton and Serializable to save the data on a hard drive.
 */
public class CharacterContainer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static CharacterContainer instance = null;
    private ArrayList<Character> container;

    /**
     * Creates an CharacterContainer Object and loads the persistent data from the file mentioned in the Serializer Class.
     * If this file doesn't exists, the Serializer CLass will create the file and the CharacterContainer will have an empty list.
     */
    private CharacterContainer() {
        try {
            this.container = Serializer.readData().getAllCharacters();
        } catch (Exception e) {
            this.container = new ArrayList<>();
        }
    }

    /**
     * @return The only instance of the CharacterContainer
     */
    public static CharacterContainer getInstance() {
        if (instance == null) {
            instance = new CharacterContainer();
        }
        return instance;
    }

    /**
     * @return ArrayList of Characters (ArrayList could be empty)
     */
    public ArrayList<Character> getAllCharacters() {
        return container;
    }

    /**
     * Saves the Current state of all Characters in CharacterContainer.
     */
    public void save() {
        Serializer.writeData(this);
    }

    /**
     * Adds a Character to the list "{@link #container}".
     * @param character Type: Character
     * @return A reference of the CharacterContainer Instance
     */
    public final CharacterContainer addCharacter(Character character) {
        this.container.add(character);
        return this;
    }

    /**
     * Searches in the Charactercontainer for every Character, who is matching to the String.
     * @param searchString
     * @return List of matching Characters (eventually a empty list)
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
     * Overwrites a specific Character with its updated version.
     * @param c Type: Character
     * @return The updated Character if update was successful and "null" if CharacterContainer does not contain the Character
     */
    public Character updateCharacter(Character c) {
        Character result = this.findById(c.getId());
        if (result != null)
            return result.acceptUpdater(new UpdateVisitor(), c);
        else
            return null;
    }

    /**
     * Deletes a Character from the CharacterContainer by its ID.
     * @param id Type: UUID
     * @return "true" when deleting successfull and "false" if not (CharacterContainer does not contain the Character)
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
     * Finds a Character in the CharacterContainer by its ID.
     * @param id Type: UUID
     * @return The Character if CharacterContainer contains the Character and "null" if not
     */
    public Character findById(UUID id) {
        for (Character character : container) {
            if (character.getId().equals(id))
                return character;
        }
        return null;
    }

    /**
     * @return All Characters from the CharacterContainer as one formatted String
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
