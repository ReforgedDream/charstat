import java.util.HashMap;
import java.util.Map;

public class TextStatistics {
    private String stringToAnalyze;

    public TextStatistics(String inputString) {
        this.stringToAnalyze = inputString;
    }

    public void analyze() {
        Map<Character, Short> stats = new HashMap<Character, Short>();

        for (int i = 0; i < this.stringToAnalyze.length(); i++) {
            Short currentCount;
            Character currentChar = this.stringToAnalyze.charAt(i);
            if (stats.containsKey(currentChar)) {
                currentCount = stats.get(currentChar);
                currentCount++;
            } else {
                currentCount = 1;
            }
            stats.put(currentChar, currentCount);
        }

        for (Map.Entry<Character, Short> entry : stats.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
