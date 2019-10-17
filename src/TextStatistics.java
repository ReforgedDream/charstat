import java.util.HashMap;
import java.util.Map;

public class TextStatistics {

    public void analyzeString(String stringToAnalyze) {
        System.out.println(stringToAnalyze);
        Map<Character, Short> statisticsMap = new HashMap<Character, Short>();
        for (int i = 0; i < stringToAnalyze.length(); i++) {
            Short currentCount;
            Character currentChar = stringToAnalyze.charAt(i);
            if (statisticsMap.containsKey(currentChar)) {
                currentCount = statisticsMap.get(currentChar);
                currentCount++;
            } else {
                currentCount = 1;
            }
            statisticsMap.put(currentChar, currentCount);
        }
        printStatistics(statisticsMap);
    }

    private void printStatistics(Map<Character, Short> mapToPrint) {
        for (Map.Entry<Character, Short> entry : mapToPrint.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
