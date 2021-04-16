package com.fhkiel.oopproject.serialize;

import java.io.*;

import com.fhkiel.oopproject.container.CharacterContainer;

/**********************************************************
 * "CLASS-Description"
 **********************************************************/
public class Serializer implements Serializable {

    // The serialVersionUID is important for version control
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String filename = "CharacterData.bode";

    public static CharacterContainer readData() {
        CharacterContainer characterData = null;
        try {
            FileInputStream fileInput = new FileInputStream(filename);
            ObjectInputStream dataInput = new ObjectInputStream(fileInput);
            characterData = (CharacterContainer) dataInput.readObject();
            dataInput.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return characterData;
    }

    public static void writeData(CharacterContainer dataToWrite) {
        try {
            FileOutputStream fileOutput = new FileOutputStream(filename);
            ObjectOutputStream dataOutput = new ObjectOutputStream(fileOutput);
            dataOutput.writeObject(dataToWrite);
            dataOutput.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // MAIN-TEST
    /*public static void main(String[] args) {
        CharacterContainer container = CharacterContainer.getInstance();

        // CREATE
        Character grogu = new Character("Grogu", "Grogu", 50);
        Character mando = new Character("Mando", "Grogu", 50);
        Character boba = new Character("Boba", "Fett", 50);
        container.addCharacter(grogu);
        container.addCharacter(boba);
        container.addCharacter(mando);
        System.out.println(container);

        Serializer.writeData(container);
    }*/
}
