package com.fhkiel.oopproject.model;

import java.util.UUID;

/**
 * Exception for spring rest service if a character was not found.
 */
public class CharacterNotFound extends RuntimeException{
    public CharacterNotFound(UUID id){
        super("Could not find Character " + id);
    }
}
