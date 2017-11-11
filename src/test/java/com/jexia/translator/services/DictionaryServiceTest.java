package com.jexia.translator.services;

import com.jexia.translator.alphabets.KlingonAlphabet;
import com.jexia.translator.exceptions.AlphabetNotFoundException;
import com.jexia.translator.exceptions.InvalidDictionaryCharacterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictionaryServiceTest {

    @Autowired
    private DictionaryService dictionaryService;

    @Test(expected = AlphabetNotFoundException.class)
    public void alphabetNotSetTest() throws InvalidDictionaryCharacterException, AlphabetNotFoundException {

        dictionaryService.setAlphabet(null);
        dictionaryService.translate("");

    }

    @Test
    public void alphabetSetTest() throws InvalidDictionaryCharacterException, AlphabetNotFoundException {

        dictionaryService.setAlphabet(new KlingonAlphabet());
        Assert.assertNotNull(dictionaryService.getAlphabet());

    }

    @Test(expected = InvalidDictionaryCharacterException.class)
    public void invalidCharacterTest() throws InvalidDictionaryCharacterException, AlphabetNotFoundException {

        dictionaryService.setAlphabet(new KlingonAlphabet());
        dictionaryService.translate("Great Minra");

    }

    @Test
    public void translateTestUhura() throws InvalidDictionaryCharacterException, AlphabetNotFoundException {

        dictionaryService.setAlphabet(new KlingonAlphabet());
        List<String> stringList = dictionaryService.translate("uhura");

        Assert.assertEquals(5, stringList.size());

        Assert.assertEquals("0xF8E5", stringList.get(0));
        Assert.assertEquals("0xF8D6", stringList.get(1));
        Assert.assertEquals("0xF8E5", stringList.get(2));
        Assert.assertEquals("0xF8E1", stringList.get(3));
        Assert.assertEquals("0xF8D0", stringList.get(4));

    }

}
