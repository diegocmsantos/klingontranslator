package com.jexia.translator.services;

import com.jexia.translator.alphabets.Alphabet;
import com.jexia.translator.exceptions.AlphabetNotFoundException;
import com.jexia.translator.exceptions.InvalidDictionaryCharacterException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {

    private Alphabet alphabet;

    public void setAlphabet(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public List<String> translate(String word) throws AlphabetNotFoundException, InvalidDictionaryCharacterException {

        if (this.alphabet == null) {
            throw new AlphabetNotFoundException();
        }

        return this.alphabet.translate(word);

    }
}
