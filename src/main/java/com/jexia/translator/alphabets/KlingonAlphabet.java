package com.jexia.translator.alphabets;

import com.jexia.translator.exceptions.InvalidDictionaryCharacterException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represents the Klingon alphabet.
 * Each alphabet is responsible for translate english into it.
 */
@Component
public class KlingonAlphabet implements Alphabet {

    private Map<String, String> dictionary;

    public KlingonAlphabet() {
        this.dictionary = createAlphabet();
    }

    /**
     * Translate an english sentence in a Klingon sentence
     * @param word - String
     * @return List - With each translated word
     * @throws InvalidDictionaryCharacterException - If any char not exists in the alphabet
     */
    @Override
    public List<String> translate(String word) throws InvalidDictionaryCharacterException {

        word = word.toLowerCase();
        List<String> result = new LinkedList<>();
        char c;
        String found;
        int stepsAhead = 0;
        String charStr;

        boolean lastChar = false;
        for (int i = 0; i < word.length(); i++) {

            lastChar = i == word.length() - 1;

            c = word.charAt(i);

            charStr = String.valueOf(c);

            if (!lastChar) {
                if ("c".equals(charStr) || "g".equals(charStr)) {
                    charStr += String.valueOf(word.charAt(i + 1));
                    stepsAhead = 1;
                } else if ("n".equals(charStr)) {
                    String g = String.valueOf(word.charAt(i + 1));
                    if ("g".equals(g)) {
                        charStr = String.valueOf(word.charAt(i + 1));
                        stepsAhead = 1;
                    }
                } else if ("t".equals(charStr)) {
                    charStr += String.valueOf(word.charAt(i + 1));
                    charStr += String.valueOf(word.charAt(i + 2));
                    stepsAhead = 2;
                }
            }

            found = dictionary.get(charStr);

            if (found == null) {
                throw new InvalidDictionaryCharacterException();
            }

            i += stepsAhead;
            stepsAhead = 0;

            result.add(found);

        }

        return result;
    }

    @Override
    public Map<String, String> createAlphabet() {

        Map<String, String> dictionary = new HashMap<>();

        dictionary.put("a", "0xF8D0");
        dictionary.put("b", "0xF8D1");
        dictionary.put("ch", "0xF8D2");
        dictionary.put("d", "0xF8D3");
        dictionary.put("e", "0xF8D4");
        dictionary.put("gh", "0xF8D5");
        dictionary.put("h", "0xF8D6");
        dictionary.put("i", "0xF8D7");
        dictionary.put("j", "0xF8D8");
        dictionary.put("l", "0xF8D9");
        dictionary.put("m", "0xF8DA");
        dictionary.put("n", "0xF8DB");
        dictionary.put("ng", "0xF8DC");
        dictionary.put("o", "0xF8DD");
        dictionary.put("p", "0xF8DE");
        dictionary.put("q", "0xF8DF");
        dictionary.put("q", "0xF8E0");
        dictionary.put("r", "0xF8E1");
        dictionary.put("s", "0xF8E2");
        dictionary.put("t", "0xF8E3");
        dictionary.put("tlh", "0xF8E4");
        dictionary.put("u", "0xF8E5");
        dictionary.put("v", "0xF8E6");
        dictionary.put("w", "0xF8E7");
        dictionary.put("y", "0xF8E8");
        dictionary.put("'", "0xF8E9");
        dictionary.put("0", "0xF8F0");
        dictionary.put("1", "0xF8F1");
        dictionary.put("2", "0xF8F2");
        dictionary.put("3", "0xF8F3");
        dictionary.put("4", "0xF8F4");
        dictionary.put("5", "0xF8F5");
        dictionary.put("6", "0xF8F6");
        dictionary.put("7", "0xF8F7");
        dictionary.put("8", "0xF8F8");
        dictionary.put("9", "0xF8F9");
        dictionary.put(",", "0xF8FD");
        dictionary.put(".", "0xF8FE");
        dictionary.put(" ", "0x0020");

        return dictionary;

    }

}
