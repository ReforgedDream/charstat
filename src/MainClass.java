public class MainClass {
    public static void main(String[] args) {
        String inputString = "aaabbbbbcdd";
        TextStatistics ts = new TextStatistics(inputString);
        ts.analyze();
    }
}
