import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainClass {
    public static void main(String[] args) {

        Path path = Paths.get(System.getProperty("user.dir") + "/text/IN");
        Path outputPath = Paths.get("text/OUT/");
        try {
            new WatchDir(path, outputPath).processEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
