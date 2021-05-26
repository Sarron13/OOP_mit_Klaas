
package com.fhkiel.oopproject.rest;



import java.util.List;
import java.util.UUID;

import com.fhkiel.oopproject.container.CharacterContainer;
import com.fhkiel.oopproject.model.Character;
import com.fhkiel.oopproject.model.CharacterNotFound;
import org.springframework.web.bind.annotation.*;

/**
 * <strong>Class-Description:</strong><br/>
 * Spring rest controller providing urls for CRUD operations on the character container
 */

@RestController
public class CharacterController {

    private final CharacterContainer container = CharacterContainer.getInstance();

    /**
     *
     * @param c json object Example:
     *          <code>
     *          {
     *              "@type": "LotR",
     *              "firstname": "Lukas",
     *              "lastname": "Klink",
     *              "age": 20,
     *              "favoriteTobacco": "Halfling's Leaf"
     *          }
     *          {
     *              "@type": "StarWars",
     *              "firstname": "Klaas",
     *              "lastname": "Pelzer",
     *              "age": 20,
     *              "spaceship": "Flying Hwak2"
     *          }
     *          </code>
     *          at "@type" you choose character class, "StarWars" or "LotR"
     */
    @PostMapping(path = "/character", consumes = "application/json", produces = "application/json")
    public void addCharacter(@RequestBody Character c) {
        container.addCharacter(c);
        container.save();
    }

    /**
     * gives all chars
     * @return json object with all characters
     */
    @GetMapping("/character")
    public List<Character> getCharacters() {
        return container.getAllCharacters();
    }

    /**
     *
     * @param id id of wanted chat
     * @return  char in json
     */
    @GetMapping("/character/{id}")
    public Character getCharacter(@PathVariable UUID id) {
        if (container.findById(id) != null)
            return container.findById(id);
        else
            throw new CharacterNotFound(id);
    }

    /**
     * updates a char in container
     * @param c json object with id and attributes, Example
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
     * searches in last- and firstnames
     * @param term search Substring
     * @return json array with all chars containing the substring in their first and lastname
     */
    @GetMapping("/character/search")
    public List<Character> searchCharacters(@RequestParam String term) {
        return container.search(term);
    }

    /**
     * deletes char from container
     * @param id id for char to delete
     */
    @DeleteMapping("character/{id}")
    public void deleteCharacter(@PathVariable UUID id) {
        if (!container.deleteByID(id))
            throw new CharacterNotFound(id);
        else
            container.save();
    }
}

