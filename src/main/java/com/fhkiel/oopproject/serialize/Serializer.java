package com.fhkiel.oopproject.serialize;

import java.io.*;

import com.fhkiel.oopproject.container.CharacterContainer;

/**
 * <strong>Class-Description:</strong>
 * <p>The Serializer-Class contains methods to write to and read a file.
 * The "writeData()" method writes the whole instance of a
 * CharacterContainer into a file (serialization), which is described
 * in the static String attribut "filename". The "readData()" method
 * reads the content of the file, which is described in the static
 * String attribut "filename" and converts this data back in to an
 * instance of "CharacterContainer" (deserialization).</p>
 */
public class Serializer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String filename = "CharacterData.bode";

    public static CharacterContainer readData() throws IOException, ClassNotFoundException {
        CharacterContainer characterData = null;
        FileInputStream fileInput = new FileInputStream(filename);
        ObjectInputStream dataInput = new ObjectInputStream(fileInput);
        characterData = (CharacterContainer) dataInput.readObject();
        dataInput.close();
        return characterData;
    }

    public static void writeData(CharacterContainer dataToWrite) {
        try {
            FileOutputStream fileOutput = new FileOutputStream(filename);
            ObjectOutputStream dataOutput = new ObjectOutputStream(fileOutput);
            dataOutput.writeObject(dataToWrite);
            dataOutput.close();
            fileOutput.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
