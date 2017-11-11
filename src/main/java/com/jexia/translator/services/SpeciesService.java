package com.jexia.translator.services;

import com.jexia.translator.models.CharacterList;
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

}
