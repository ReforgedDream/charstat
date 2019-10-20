package utils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A singleton file writer class
 */
public class FileWriter {
    private static FileWriter instance;

    public static synchronized FileWriter getInstance() {
        if (instance == null) {
            instance = new FileWriter();
        }
        return instance;
    }

    /**
     * Compose path to output dir and write the string to new file. Creates output dir if doesn't exists
     *
     * @param fileName    a name for the output file
     * @param lineToWrite string to be written
     */
    public void writeToFile(String fileName, Path outputPath, String lineToWrite) {
        if (fileName != null && !fileName.isEmpty() &&
                lineToWrite != null && !lineToWrite.isEmpty()) {

            byte[] data = lineToWrite.getBytes();

            Path pathToFile = outputPath.resolve(fileName);
            if (!outputPath.toFile().exists()) {
                try {
                    Files.createDirectory(outputPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(pathToFile))) {
                out.write(data, 0, data.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
