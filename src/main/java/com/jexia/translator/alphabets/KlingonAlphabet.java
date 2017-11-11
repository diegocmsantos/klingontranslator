package com.jexia.translator.alphabets;

import com.jexia.translator.exceptions.InvalidDictionaryCharacterException;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class KlingonAlphabet implements Alphabet {

    private Map<String, String> dictionary;

    public KlingonAlphabet() {
        this.dictionary = createAlphabet();
    }

    @Override
    public List<String> translate(String word) throws InvalidDictionaryCharacterException {

        word = word.toLowerCase();
        List<String> result = new LinkedList<>();
        char c;
        String found;
        int stepsAhead = 0;
        String charStr;

        for (int i = 0; i < word.length(); i++) {

            c = word.charAt(i);

            charStr = String.valueOf(c);

            if ("c".equals(charStr) || "g".equals(charStr))  {
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
        return null;
    }

}
