package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.List;
import java.util.Map;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }
    @Override
    /* Returns the most common letter in the set of valid words based on the current
       PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        List<String> filterWords = LFGHelper.getMatchedWords(words, pattern);
        LFGHelper.furtherFilterWords(filterWords, guesses, pattern);
        Map<Character, Integer> countMap = LFGHelper.getFrequencyMap(filterWords);
        return LFGHelper.maxFreCharacter(guesses, countMap);
    }

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
