package com.fhkiel.oopproject.rest;


import java.util.List;
import java.util.UUID;

import com.fhkiel.oopproject.container.CharacterContainer;
import com.fhkiel.oopproject.model.Character;
import com.fhkiel.oopproject.model.CharacterNotFound;
import com.fhkiel.oopproject.serialize.Serializer;
import org.springframework.web.bind.annotation.*;

/**
 * Spring rest controller providing urls for CRUD operations on the character container
 *
 */

@RestController
public class CharacterController {

    private final CharacterContainer container = CharacterContainer.getInstance();

    /**
     *
     * @param c json object with format:
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
     *          at "@type" you choose character class, "StarWars" or "LotR"
      */
    @PostMapping(path = "/character", consumes = "application/json", produces = "application/json")
    public void addCharacter(@RequestBody Character c) {
        container.addCharacter(c);
        container.save();
    }

    /**
     *
     * @return json object with all characters
     */
    @GetMapping("/character")
    public List<Character> getCharacters() {
        return container.getAllCharacters();
    }

    @GetMapping("/character/{id}")
    public Character getCharacter(@PathVariable UUID id) {
        if (container.findById(id) != null)
            return container.findById(id);
        else
            throw new CharacterNotFound(id);
    }

    /**
     *
     * @param c json object same as post method
     */
    @PutMapping("/character")
    public void updateCharacter(@RequestBody Character c) {
        if (container.updateCharacter(c) == null)
            throw new CharacterNotFound(c.getId());
        else
            container.save();
    }

    @GetMapping("/character/search")
    public List<Character> searchCharacters(@RequestParam String term) {
        return container.search(term);
    }

    @DeleteMapping("character/{id}")
    public void deleteCharacter(@PathVariable UUID id) {
        if (!container.deleteByID(id))
            throw new CharacterNotFound(id);
        else
            container.save();
    }
}

