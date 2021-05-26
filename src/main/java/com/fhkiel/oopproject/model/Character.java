package com.fhkiel.oopproject.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
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

    public Character(final String firstname, final String lastname, final int age) {
        this.id = UUID.randomUUID();
        this.setFirstname(firstname).setLastname(lastname).setAge(age);

    }

    /**
     * abstract method for a Visitor-Interface. Will be implemented in concrete childclasses.
     * @param v Type: UptadeVisitor
     * @param c Type: Character
     * @return /
     */
    public abstract Character acceptUpdater(UpdateVisitor v, Character c);

    /**
     * Checks if the searchString matches completely or partly with the firstname or lastname of the Character.
     * @param searchString
     * @return "true" if the searchString matches completely or partly with the firstname or lastname and "false" if not
     */
    public boolean matches(String searchString) {
        return this.matchesFirstname(searchString) || this.matchesLastname(searchString);
    }

    /**
     * Checks if the searchString matches completely or partly with the firstname of the Character.
     * @param searchString
     * @return "true" if matching completely or partly and "false" if not
     */
    public boolean matchesFirstname(String searchString) {
        return this.getFirstname().toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT));
    }

    /**
     * Checks if the searchString matches completely or partly with the lastname of the Character.
     * @param searchString
     * @return "true" if matching completely or partly and "false" if not
     */
    public boolean matchesLastname(String searchString) {
        return this.getLastname().toLowerCase(Locale.ROOT).contains(searchString.toLowerCase(Locale.ROOT));
    }

    /**
     * @return The ID as UUID
     */
    public UUID getId() {
        return id;
    }

    // TODO: Ask Klaas if all getter and setter needs Java-Doc according to Mr.Peins Answere
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

    /**
     *
     * @param o Type: Object
     * @return "true" if its the same object or if its an instance of {@link Character} and the age, firstname and lastname matches, otherwise "false"
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
     * @return Hash-Code as {@link Integer}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, getFirstname(), getLastname(), getAge());
    }

    /**
     * @return A String with the {@link #firstname}, {@link #lastname} and {@link #age} of the Character-Instance.
     */
    @Override
    public String toString() {
        return "firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + ", age=" + age;
    }
}
