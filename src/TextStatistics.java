import java.util.*;

class TextStatistics {

    /**
     * Method that compose a textual report about input string
     *
     * @param stringToAnalyze a string to be analyzed
     * @return Human-readable report as String
     */
    static String analyzeString(String stringToAnalyze) {
        System.out.println(stringToAnalyze);

        List<Map.Entry<Character, Short>> list = countAndSortLetters(stringToAnalyze);

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Character, Short> entry : list) {
            stringBuilder.append(entry.getKey());
            stringBuilder.append(": ");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("\r\n");
        }

        stringBuilder.append("Number of words: ");
        stringBuilder.append(getNumberOfWords(stringToAnalyze));

        return stringBuilder.toString();
    }

    /**
     * Analyzes number of each letter in input string
     *
     * @param inputString a string to be analyzed
     * @return List sorted by descendant number of entries
     */
    private static List<Map.Entry<Character, Short>> countAndSortLetters(String inputString) {
        Map<Character, Short> statisticsMap = new HashMap<>();
        for (int i = 0; i < inputString.length(); i++) {

            Character currentChar = inputString.charAt(i);

            if (Character.isLetter(currentChar)) {
                Short currentCount = 1;
                if (statisticsMap.containsKey(currentChar)) {
                    currentCount = statisticsMap.get(currentChar);
                    currentCount++;
                }
                statisticsMap.put(currentChar, currentCount);
            }
        }
        List<Map.Entry<Character, Short>> list = new ArrayList<>(statisticsMap.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return list;
    }

    /**
     * Counts number of words. Preposition signs such as standalone & considered non-words
     *
     * @param inputString a string to be analyzed
     * @return number of words
     */
    private static short getNumberOfWords(String inputString) {
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
