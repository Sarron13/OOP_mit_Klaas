package core;

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
}
