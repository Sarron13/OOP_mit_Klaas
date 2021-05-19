package com.fhkiel.oopproject.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

/**
 * Exception for spring rest service if a character was not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CharacterNotFound extends RuntimeException{
    public CharacterNotFound(UUID id){
        super("Could not find Character " + id);
    }
}
