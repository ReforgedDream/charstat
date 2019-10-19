import utils.FileReader;

public class MainClass {
    public static void main(String[] args) {

        FileReader fr = new FileReader(System.getProperty("user.dir") + "/text/IN/input.txt");
        String inputString = fr.ReadFile();

        TextStatistics ts = new TextStatistics();
        ts.analyzeString(inputString);
    }
}
