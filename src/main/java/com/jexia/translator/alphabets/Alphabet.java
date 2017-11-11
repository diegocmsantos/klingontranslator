package com.jexia.translator.alphabets;

import com.jexia.translator.exceptions.InvalidDictionaryCharacterException;

import java.util.List;
import java.util.Map;

public interface Alphabet {

    List<String> translate(String word) throws InvalidDictionaryCharacterException;

    Map<String, String> createAlphabet();

}
