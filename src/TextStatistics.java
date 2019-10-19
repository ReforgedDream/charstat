import utils.FileWriter;

import java.util.*;

public class TextStatistics {

    public void analyzeString(String stringToAnalyze) {
        System.out.println(stringToAnalyze);
        Map<Character, Short> statisticsMap = new HashMap<>();
        for (int i = 0; i < stringToAnalyze.length(); i++) {
            Short currentCount = 0;
            Character currentChar = stringToAnalyze.charAt(i);
            if (Character.isLetter(currentChar)) {
                if (statisticsMap.containsKey(currentChar)) {
                    currentCount = statisticsMap.get(currentChar);
                    currentCount++;
                } else {
                    currentCount = 1;
                }
                statisticsMap.put(currentChar, currentCount);
            }
        }

        List<Map.Entry<Character, Short>> list = new LinkedList(statisticsMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Character, Short>>() {
            public int compare(Map.Entry<Character, Short> o1, Map.Entry<Character, Short> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Short> entry : list) {
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append("\r\n");
        }

        sb.append("Number of words: ");
        sb.append(getNumberOfWords(stringToAnalyze));

        printResults(sb.toString());
        saveResults(sb.toString());
    }

    private void printResults(String results) {
        System.out.println(results);
    }

    private void saveResults(String results) {
        FileWriter fr = FileWriter.getInstance();
        fr.writeToFile("input", results);
    }

    private short getNumberOfWords(String inputString) {
        String regexpWordSeparator = "\\s";
        String regexpNotWord = "\\p{Punct}+";
        String[] words = inputString.split(regexpWordSeparator);
        Short numberOfWords = 0;
        for (String word : words) {
            if (word.isEmpty() || word.matches(regexpNotWord)) {
                continue;
            }
            numberOfWords++;
        }
        return numberOfWords;
    }
}
