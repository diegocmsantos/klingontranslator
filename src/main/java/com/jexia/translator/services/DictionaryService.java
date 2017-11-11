package com.jexia.translator.services;

import com.jexia.translator.alphabets.Alphabet;
import com.jexia.translator.exceptions.AlphabetNotFoundException;
import com.jexia.translator.exceptions.InvalidDictionaryCharacterException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to translate words regarding the alphabet configured.
 * The ideal architecture would be this dictionary receiving two alphabets.
 * One to be the reference and the other to be translated.
 * Like portuguese-english and english-portuguese dictionary.
 * But for simplicity I've assumed that it always be an english-klingon dictionary.
 */
@Service
public class DictionaryService {

    private Alphabet alphabet;

    public void setAlphabet(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    /**
     * Translates a sentence from english to klingon.
     * @param word - String.
     * @return List - With each word in sentence translated.
     * @throws AlphabetNotFoundException - Alphabet was not set up previously.
     * @throws InvalidDictionaryCharacterException - If a char not exists in the specified alphabet.
     */
    public List<String> translate(String word) throws AlphabetNotFoundException, InvalidDictionaryCharacterException {

        if (this.alphabet == null) {
            throw new AlphabetNotFoundException();
        }

        return this.alphabet.translate(word);

    }
}
