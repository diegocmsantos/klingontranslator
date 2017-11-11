package com.jexia.translator;

import com.jexia.translator.alphabets.KlingonAlphabet;
import com.jexia.translator.exceptions.AlphabetNotFoundException;
import com.jexia.translator.exceptions.InvalidDictionaryCharacterException;
import com.jexia.translator.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TranslatorApplication implements CommandLineRunner {

    @Autowired
    public DictionaryService dictionaryService;

	public static void main(String[] args) {
		SpringApplication.run(TranslatorApplication.class, args);
	}

    @Override
    public void run(String... args) {

        String wholeSentence = "";
	    if (args.length > 0) {

	        try {

	            wholeSentence = String.join(" ", args);

	            dictionaryService.setAlphabet(new KlingonAlphabet());
                List<String> translatedWord = dictionaryService.translate(wholeSentence);

                System.out.println(String.join(" ", translatedWord));
                System.out.println();

            } catch (AlphabetNotFoundException e) {
                System.out.println("Alphabet is not set in dictionary.");
            } catch (InvalidDictionaryCharacterException e) {
                System.out.println("Sentence '" + wholeSentence + "' has one or more characters that not exists in Klingon alphabet.");
            }

        } else {
            System.out.println("No argument was passed");
        }

    }
}
