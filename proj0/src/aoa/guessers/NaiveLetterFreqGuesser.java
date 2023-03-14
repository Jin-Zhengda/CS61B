package aoa.guessers;

import aoa.utils.FileUtils;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /**
     * Returns a map from a given letter to its frequency across all words.
     * This task is similar to something you did in hw0b!
     */
    public Map<Character, Integer> getFrequencyMap() {
        Map<Character, Integer> countMap = new TreeMap<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!countMap.containsKey(ch)) {
                    countMap.put(ch, 1);
                } else {
                    countMap.put(ch, countMap.get(ch) + 1);
                }
            }
        }
        return countMap;
    }

    /**
     * Returns the most common letter in WORDS that has not yet been guessed
     * (and therefore isn't present in GUESSES).
     */
    public char getGuess(List<Character> guesses) {
        Map<Character, Integer> countMap;
        countMap = getFrequencyMap();
        if (countMap.isEmpty()) {
            return '?';
        }
        for (char guess : guesses) {
            countMap.remove(guess);
        }
        char firstKey = countMap.keySet().iterator().next();
        int m = countMap.get(firstKey);
        char ch = firstKey;
        for (char key : countMap.keySet()) {
            if (countMap.get(key) > m) {
                m = countMap.get(key);
                ch = key;
            }
        }
        return ch;
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
