package com.fhkiel.oopproject.model;

import java.util.UUID;

public class CharacterNotFound extends RuntimeException{
    public CharacterNotFound(UUID id){
        super("Could not find Character " + id);
    }
}
