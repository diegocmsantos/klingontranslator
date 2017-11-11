package com.jexia.translator;

import com.jexia.translator.alphabets.KlingonAlphabet;
import com.jexia.translator.exceptions.AlphabetNotFoundException;
import com.jexia.translator.exceptions.InvalidDictionaryCharacterException;
import com.jexia.translator.models.CharacterList;
import com.jexia.translator.services.DictionaryService;
import com.jexia.translator.services.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class TranslatorApplication implements CommandLineRunner {

    @Autowired
    public DictionaryService dictionaryService;

    @Autowired
    public SpeciesService speciesService;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

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

                CharacterList characterList = speciesService.getCharacterList(wholeSentence);
                String species = speciesService.getCharacterSpecies(characterList);
                System.out.println(!species.isEmpty() ? species : "Species not found.");

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
