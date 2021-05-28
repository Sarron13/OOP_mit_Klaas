package com.fhkiel.oopproject.serialize;

import java.io.*;

import com.fhkiel.oopproject.container.CharacterContainer;

/**
 * <strong>Class-Description:</strong><br/>
 * The Serializer-Class contains methods to write to and read a file.
 * The "writeData()" method writes the whole instance of a
 * CharacterContainer into a file (serialization), which is described
 * in the static String attribut "filename". The "readData()" method
 * reads the content of the file, which is described in the static
 * String attribut "filename" and converts this data back in to an
 * instance of "CharacterContainer" (deserialization). <br/>
 * The Serializer-Class implements the Serializable-Interface.
 */
 // TODO: Maybe short the description.
public class Serializer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Type: String <br/>
     * The Filename states the file, where the data is/will be saved.
     */
    private static final String filename = "CharacterData.bode";

    /**
     * Read Data from hard drive. If no Data exists, the method creates the file mentioned in the String-Attribute {@link #filename}.
     * @return {@link CharacterContainer CharacterContainer-Data}
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static CharacterContainer readData() throws IOException, ClassNotFoundException {
        CharacterContainer characterData = null;
        FileInputStream fileInput = new FileInputStream(filename);
        ObjectInputStream dataInput = new ObjectInputStream(fileInput);
        characterData = (CharacterContainer) dataInput.readObject();
        dataInput.close();
        return characterData;
    }

    /**
     * Write Data to the file on the hard drive, which is mentioned in the String-Attribute {@link #filename}.
     * @param dataToWrite (Instance of {@link CharacterContainer})
     */
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
