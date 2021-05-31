
package com.fhkiel.oopproject.rest;



import java.util.List;
import java.util.UUID;

import com.fhkiel.oopproject.container.CharacterContainer;
import com.fhkiel.oopproject.model.Character;
import com.fhkiel.oopproject.model.CharacterNotFound;
import org.springframework.web.bind.annotation.*;

/**
 * <strong>Class-Description:</strong><br/>
 * Spring rest controller providing URLs for CRUD operations on the {@link CharacterContainer character container}.
 */
@RestController
public class CharacterController {

    private final CharacterContainer container = CharacterContainer.getInstance();

    /**
     * Adds the transmitted character to the {@link CharacterContainer}.
     * @param c json object Example:
     *          <code>
     *          {
     *              "@type": "{@link com.fhkiel.oopproject.model.LordOfRingsChar LotR}",
     *              "firstname": "Lukas",
     *              "lastname": "Klink",
     *              "age": 20,
     *              "favoriteTobacco": "Halfling's Leaf"
     *          }
     *          {
     *              "@type": "{@link com.fhkiel.oopproject.model.StarWarsChar StarWars}",
     *              "firstname": "Klaas",
     *              "lastname": "Pelzer",
     *              "age": 20,
     *              "spaceship": "Flying Hwak2"
     *          }
     *          </code>
     *          at "@type" you choose character class, "{@link com.fhkiel.oopproject.model.StarWarsChar StarWars}" or "{@link com.fhkiel.oopproject.model.LordOfRingsChar LotR}"
     */
    @PostMapping(path = "/character", consumes = "application/json", produces = "application/json")
    public void addCharacter(@RequestBody Character c) {
        container.addCharacter(c);
        container.save();
    }

    /**
     * Gives all characters from the {@link CharacterContainer}.
     * @return json object with all characters
     */
    @GetMapping("/character")
    public List<Character> getCharacters() {
        return container.getAllCharacters();
    }

    /** TODO: *here is something you might need to correct*
     * Gets a specific character by its {@link UUID id} from the {@link CharacterContainer}.
     * @param id id of wanted char Type: {@link UUID}
     * @return  character in json
     */
    @GetMapping("/character/{id}")
    public Character getCharacter(@PathVariable UUID id) {
        if (container.findById(id) != null)
            return container.findById(id);
        else
            throw new CharacterNotFound(id);
    }

    /**
     * Updates a specific character in the {@link CharacterContainer}.
     * @param c json object with id and attributes, Example:
     *      <code>
     *      {
     *         "@type": "StarWars",
     *         "id": "c1aaa023-a735-4b1f-9b9f-895e2c970dc8",
     *         "firstname": "Klaas",
     *         "lastname": "Pelzer1",
     *         "age": 21,
     *         "spaceship": "Flying Hwak2"
     *     }
     *     </code>
     */
    @PutMapping("/character")
    public void updateCharacter(@RequestBody Character c) {
        if (container.updateCharacter(c) == null)
            throw new CharacterNotFound(c.getId());
        else
            container.save();
    }

    /**
     * Searches for characters by their {@link com.fhkiel.oopproject.model.Character#lastname last-} and/or {@link com.fhkiel.oopproject.model.Character#firstname firstnames} in the {@link CharacterContainer}.
     * @param term search Substring Type: {@link String}
     * @return json array with all characters containing the substring in their first- and/or lastname
     */
    @GetMapping("/character/search")
    public List<Character> searchCharacters(@RequestParam String term) {
        return container.search(term);
    }

    //TODO: Should the ID in the description linked to UUID or to the id attribute of Character?
    /**
     * Deletes a character from {@link CharacterContainer container} by its {@link com.fhkiel.oopproject.model.Character#id id}.
     * @param id id for char to delete Type: {@link UUID}
     */
    @DeleteMapping("character/{id}")
    public void deleteCharacter(@PathVariable UUID id) {
        if (!container.deleteByID(id))
            throw new CharacterNotFound(id);
        else
            container.save();
    }
}

