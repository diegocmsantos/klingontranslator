package com.jexia.translator.alphabets;

import java.util.List;
import java.util.Map;

public interface Alphabet {

    List<String> translate(String word);

    Map<String, String> createAlphabet();

}
