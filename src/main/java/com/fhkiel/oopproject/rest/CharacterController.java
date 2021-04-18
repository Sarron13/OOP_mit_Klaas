package com.fhkiel.oopproject.rest;


import java.util.List;
import java.util.UUID;

import com.fhkiel.oopproject.container.CharacterContainer;
import com.fhkiel.oopproject.model.Character;
import com.fhkiel.oopproject.model.CharacterNotFound;
import org.springframework.web.bind.annotation.*;

@RestController
public class CharacterController {

    private final CharacterContainer container = CharacterContainer.getInstance();

    @PostMapping(path = "/character", consumes = "application/json", produces = "application/json")
    public void addCharacter(@RequestBody Character c) {
        container.addCharacter(c);
    }

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

    @PutMapping("/character")
    public void updateCharacter(@RequestBody Character c){
        if(container.updateCharacter(c) == null)
            throw new CharacterNotFound(c.getId());
    }

    @GetMapping("/character/search")
    public List<Character> searchCharacters(@RequestParam String term) {
        return container.search(term);
    }

    // To Do:
    //  - add insert-Mapping


    //Not tested yet
    @DeleteMapping("character/{id}")
    public void deleteCharacter(@PathVariable UUID id) {
        if (!container.deleteByID(id)) {
            throw new CharacterNotFound(id);
        }
    }
}

