package core.rest;

import core.model.Character;
import core.container.CharacterContainer;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.UUID;

public class CharacterControllerImpl implements CharacterController {

    public static CharacterContainer container = CharacterContainer.getInstance();

    @Override
    public void create(UriInfo uriInfo, Character character) {
        container.addCharacter(character);
    }

    @Override
    public Character read(UUID id) {
        Character c = container.findById(id);
        if (c == null)
            throw new NotFoundException();
        else
            return c;
    }

    @Override
    public Collection<Character> getAll() {
        return container.getAllCharacters();
    }

    @Override
    public void update(Character c) {
        if (container.updateCharacter(c) == null)
            throw new NotFoundException();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Collection<Character> search(String query) {
        return null;
    }
}
