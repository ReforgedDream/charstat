package utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Helper class to read files to string.
 */
public class FileReader {
    private static final Charset encoding = Charset.defaultCharset();

    /**
     * Reader method. Throws FileSystemException, giving a possibility to ignore if file can't be read.
     *
     * @param path a path to file that should be read
     * @return String content of the file
     * @throws FileSystemException say if file can't be read
     */
    public static String ReadFile(Path path) throws FileSystemException {
        byte[] encoded = {};
        try {
            encoded = Files.readAllBytes(path);
        } catch (FileSystemException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, encoding);
    }
}
