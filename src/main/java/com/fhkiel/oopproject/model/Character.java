package com.fhkiel.oopproject.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

/**
 * <strong>Class-Description:</strong><br/>
 * Abstract class for all characters providing methods for name matching and updating.<br/>
 * The Character-Class implements the Serializable-Interface.
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

    /**
     * Sets the {@link #firstname}, {@link #lastname} and {@link #age} of the Character.
     * @param firstname Type: {@link String}
     * @param lastname Type: {@link String}
     * @param age Type: int
     */
    public Character(final String firstname, final String lastname, final int age) {
        this.id = UUID.randomUUID();
        this.setFirstname(firstname).setLastname(lastname).setAge(age);

    }

    /**
     * abstract method for a Visitor-Interface. Will be implemented in concrete childclasses.
     * @param v Type: {@link UpdateVisitor}
     * @param c Type: {@link Character}
     * @return /
     */
    public abstract Character acceptUpdater(UpdateVisitor v, Character c);

    /**
     * Checks if the searchString matches completely or partly with the {@link #firstname} or {@link #lastname} of the Character.
     * @param searchString Type: {@link String}
     * @return "true" if the searchString matches completely or partly with the firstname or lastname and "false" if not
     */
    public boolean matches(String searchString) {
        return this.matchesFirstname(searchString) || this.matchesLastname(searchString);
    }

    /**
     * Checks if the searchString matches completely or partly with the {@link #firstname} of the Character.
     * @param searchString Type: {@link String}
     * @return "true" if matching completely or partly and "false" if not
     */
    public boolean matchesFirstname(String searchString) {
        return this.getFirstname().toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT));
    }

    /**
     * Checks if the searchString matches completely or partly with the {@link #lastname} of the Character.
     * @param searchString Type: {@link String}
     * @return "true" if matching completely or partly and "false" if not
     */
    public boolean matchesLastname(String searchString) {
        return this.getLastname().toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT));
    }

    /**
     * @return The {@link #id} as {@link UUID}
     */
    public UUID getId() {
        return id;
    }

    /**
     * @return The {@link #firstname} as {@link String}
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname  Type: {@link String}
     * @return Reference of the current Instance of {@link Character}
     */
    public final Character setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    /**
     * @return The {@link #lastname} as {@link String}
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname Type: {@link String}
     * @return Reference of the current Instance of {@link Character}
     */
    public final Character setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    /**
     * @return The {@link #age} as int
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age Type: int
     * @return Reference of the current Instance of {@link Character}
     */
    public final Character setAge(int age) {
        this.age = age;
        return this;
    }

    /**
     *
     * @param o Type: Object
     * @return "true" if its the same object or if its an instance of {@link Character} and the {@link #age}, {@link #firstname} and {@link #lastname} matches, otherwise "false"
     */
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

    /**
     * Creates the Hash-Code for its instance form the {@link #id}, {@link #firstname}, {@link #lastname} and {@link #age}.
     * @return Hash-Code as int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, getFirstname(), getLastname(), getAge());
    }

    /**
     * @return A String with the {@link #firstname}, {@link #lastname} and {@link #age} of the {@link Character Character-Instance}.
     */
    @Override
    public String toString() {
        return "firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + ", age=" + age;
    }
}
