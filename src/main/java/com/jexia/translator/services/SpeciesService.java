package com.jexia.translator.services;

import com.jexia.translator.models.Character;
import com.jexia.translator.models.CharacterList;
import com.jexia.translator.models.CharacterRoot;
import com.jexia.translator.models.CharacterSpecies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpeciesService {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://stapi.co/api/v1/rest/character";

    public CharacterList getCharacterList(String characterName) {

        CharacterList characterList;
        String requestParams = "title=" + characterName + "&name=" + characterName;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<>(requestParams, headers);

        characterList = restTemplate.postForObject(URL + "/search", request, CharacterList.class);

        return characterList;
    }

    public String getCharacterSpecies(CharacterList characterList) {

        String characterSpeciesStr = "";
        boolean found = false;

        Character innerCharacter;
        for (Character character : characterList.getCharacters()) {

            innerCharacter = getCharacterByUID(character.getUid());

            for (CharacterSpecies characterSpecies : innerCharacter.getCharacterSpecies()) {

                if (characterSpecies.getName() != null && !characterSpecies.getName().isEmpty()) {
                    characterSpeciesStr = characterSpecies.getName();
                    found = true;
                    break;
                }

            }

            if (found) {
                break;
            }

        }

        return characterSpeciesStr;

    }

    private Character getCharacterByUID(String uid) {

        String uidRequest = "uid=" + uid;

        CharacterRoot characterRoot = this.restTemplate.getForObject(this.URL + "?" + uidRequest, CharacterRoot.class);

        if (characterRoot != null) {
            return characterRoot.getCharacter();
        }

        return new Character();

    }
}
