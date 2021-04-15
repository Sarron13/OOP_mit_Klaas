package com.fhkiel.oopproject.rest;


import java.util.List;

import com.fhkiel.oopproject.container.CharacterContainer;
import com.fhkiel.oopproject.model.Character;
import org.springframework.web.bind.annotation.*;

@RestController
public class CharacterController {

    private final CharacterContainer container = CharacterContainer.getInstance();

    @PostMapping(path = "/character", consumes = "application/json", produces = "application/json")
    public void addPerson(@RequestBody Character c){
        container.addCharacter(c);
    }

    @GetMapping("/character")
    public List<Character> getPersons(){
        return container.getAllCharacters();
    }
}

