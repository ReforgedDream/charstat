import utils.FileReader;

public class MainClass {
    public static void main(String[] args) {

        FileReader fr = new FileReader("..\\samples\\alphabetic.txt");
        String inputString = fr.ReadFile();

        TextStatistics ts = new TextStatistics();
        ts.analyzeString(inputString);
    }
}
