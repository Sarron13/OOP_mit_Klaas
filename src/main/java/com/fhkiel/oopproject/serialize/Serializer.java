package com.fhkiel.oopproject.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.fhkiel.oopproject.container.CharacterContainer;

/**********************************************************
 * "CLASS-Description"
 **********************************************************/
public class Serializer implements Serializable {

    // The serialVersionUID is important for version control
    private static final long serialVersionUID = 1L;

    private FileOutputStream fileOutput = null;
    private FileInputStream fileInput = null;
    private ObjectOutputStream dataOutput = null;
    private ObjectInputStream dataInput = null;

    public Serializer(String filename) {
        fileOutput = new FileOutputStream(filename);
        dataOutput = new ObjectOutputStream(fileOutput);

        fileInput = new FileInputStream(filename);
        dataInput = new ObjectInputStream(fileInput);
    }

    // Is not tested
    public CharacterContainer readData(CharacterContainer characterData) {
        try {
            characterData = (Character) dataInput.readObject();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return characterData;
    }

    // Is not tested
    public void writeData(CharacterContainer dataToWrite) {
        try {
            dataOutput.writeObject(dataToWrite);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static int serializeData() {
        return 0;
    }

}
