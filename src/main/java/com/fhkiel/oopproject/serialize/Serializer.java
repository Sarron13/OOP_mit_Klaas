package com.fhkiel.oopproject.serialize;

import java.io.*;

import com.fhkiel.oopproject.container.CharacterContainer;

/********************************************************************
 * Class-Description:
 * The Serializer-Class contains methods to write to and read a file.
 * The "writeData()" method writes the whole instance of a
 * CharacterContainer into a file (serialization), which is described
 * in the static String attribut "filename". The "readData()" method
 * reads the content of the file, which is described in the static
 * String attribut "filename" and converts this data back in to an
 * instance of "CharacterContainer" (deserialization).
 *******************************************************************/
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

}
