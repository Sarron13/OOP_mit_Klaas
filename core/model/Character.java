package core.model;

import java.util.Objects;
import java.util.UUID;

public class Character {
    private final UUID id;
    private String firstname;
    private String lastname;
    private int age;
    public Character(final String fistname, final String lastname, final int age) {
        this.id = UUID.randomUUID();
        this.setFirstname(fistname).setLastname(lastname).setAge(age);

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
