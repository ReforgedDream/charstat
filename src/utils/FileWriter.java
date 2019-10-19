package utils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriter {

    private static FileWriter instance;

    public static synchronized FileWriter getInstance() {
        if (instance == null) {
            instance = new FileWriter();
        }
        return instance;
    }

    public void writeToFile(String fileName, String lineToWrite) {
        // convert the string to a byte array
        byte data[] = lineToWrite.getBytes();
        //A path to file
        String path = "text/OUT/" + fileName + ".txt";
        Path file = Paths.get(path);

        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(file));) {
            //append the input string
            out.write(data, 0, data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
