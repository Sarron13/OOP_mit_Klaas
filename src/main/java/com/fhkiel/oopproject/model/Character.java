package com.fhkiel.oopproject.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

/**
 * <strong>Class-Description:</strong><br/>
 * Abstract class for all characters providing methods for name matching and updating
 */


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = LordOfRingsChar.class, name = "LotR"),
        @JsonSubTypes.Type(value = StarWarsChar.class, name ="StarWars")
})
public abstract class Character implements Serializable {

    // The serialVersionUID is important for version control
    private static final long serialVersionUID = 1L;

    private final UUID id;
    private String firstname;
    private String lastname;
    private int age;

    public Character(final String firstname, final String lastname, final int age) {
        this.id = UUID.randomUUID();
        this.setFirstname(firstname).setLastname(lastname).setAge(age);

    }


    public abstract Character acceptUpdater(UpdateVisitor v, Character c);

    public boolean matches(String searchString) {
        return this.matchesFirstname(searchString) || this.matchesLastname(searchString);
    }

    public boolean matchesFirstname(String searchString) {
        return this.getFirstname().toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT));
    }

    public boolean matchesLastname(String searchString) {
        return this.getLastname().toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT));
    }

    public UUID getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public final Character setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public final Character setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public int getAge() {
        return age;
    }

    public final Character setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Character character))
            return false;
        return getAge() == character.getAge() && Objects.equals(id, character.id)
                && Objects.equals(getFirstname(), character.getFirstname())
                && Objects.equals(getLastname(), character.getLastname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getFirstname(), getLastname(), getAge());
    }

    @Override
    public String toString() {
        return "firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + ", age=" + age;
    }
}
