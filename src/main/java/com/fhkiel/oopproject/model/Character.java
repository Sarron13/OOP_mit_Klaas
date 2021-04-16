package com.fhkiel.oopproject.model;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class Character {
    private final UUID id;
    private String firstname;
    private String lastname;
    private int age;

    public Character(final String firstname, final String lastname, final int age) {
        this.id = UUID.randomUUID();
        this.setFirstname(firstname).setLastname(lastname).setAge(age);

    }

    public final Character update(Character c) {
        this.setFirstname(c.getFirstname()).setLastname(c.getLastname()).setAge(c.getAge());
        return this;
    }

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
        if (this == o) return true;
        if (!(o instanceof Character)) return false;
        Character character = (Character) o;
        return getAge() == character.getAge() && Objects.equals(id, character.id) && Objects.equals(getFirstname(), character.getFirstname()) && Objects.equals(getLastname(), character.getLastname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getFirstname(), getLastname(), getAge());
    }

    @Override
    public String toString() {
        return "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age;
    }
}
