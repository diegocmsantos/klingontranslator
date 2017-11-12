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

    @Test
    public void translateWordWithTLHInTheMiddleTest() throws InvalidDictionaryCharacterException, AlphabetNotFoundException {

        dictionaryService.setAlphabet(new KlingonAlphabet());
        List<String> stringList = dictionaryService.translate("betlhopos");

        Assert.assertEquals(7, stringList.size());

        Assert.assertEquals("0xF8D1", stringList.get(0));
        Assert.assertEquals("0xF8D4", stringList.get(1));
        Assert.assertEquals("0xF8E4", stringList.get(2));
        Assert.assertEquals("0xF8DD", stringList.get(3));
        Assert.assertEquals("0xF8DE", stringList.get(4));
        Assert.assertEquals("0xF8DD", stringList.get(5));
        Assert.assertEquals("0xF8E2", stringList.get(6));

    }

    @Test
    public void translateWordWithTLHInTheEndTest() throws InvalidDictionaryCharacterException, AlphabetNotFoundException {

        dictionaryService.setAlphabet(new KlingonAlphabet());
        List<String> stringList = dictionaryService.translate("beopostlh");

        Assert.assertEquals(7, stringList.size());

        Assert.assertEquals("0xF8D1", stringList.get(0));
        Assert.assertEquals("0xF8D4", stringList.get(1));
        Assert.assertEquals("0xF8DD", stringList.get(2));
        Assert.assertEquals("0xF8DE", stringList.get(3));
        Assert.assertEquals("0xF8DD", stringList.get(4));
        Assert.assertEquals("0xF8E2", stringList.get(5));
        Assert.assertEquals("0xF8E4", stringList.get(6));

    }

    @Test
    public void translateWordWithCHInTheMiddleTest() throws InvalidDictionaryCharacterException, AlphabetNotFoundException {

        dictionaryService.setAlphabet(new KlingonAlphabet());
        List<String> stringList = dictionaryService.translate("ajorchumn");

        Assert.assertEquals(8, stringList.size());

        Assert.assertEquals("0xF8D0", stringList.get(0));
        Assert.assertEquals("0xF8D8", stringList.get(1));
        Assert.assertEquals("0xF8DD", stringList.get(2));
        Assert.assertEquals("0xF8E1", stringList.get(3));
        Assert.assertEquals("0xF8D2", stringList.get(4));
        Assert.assertEquals("0xF8E5", stringList.get(5));
        Assert.assertEquals("0xF8DA", stringList.get(6));
        Assert.assertEquals("0xF8DB", stringList.get(7));

    }

    @Test
    public void translateWordWithCHInTheEndTest() throws InvalidDictionaryCharacterException, AlphabetNotFoundException {

        dictionaryService.setAlphabet(new KlingonAlphabet());
        List<String> stringList = dictionaryService.translate("ajorumnch");

        Assert.assertEquals(8, stringList.size());

        Assert.assertEquals("0xF8D0", stringList.get(0));
        Assert.assertEquals("0xF8D8", stringList.get(1));
        Assert.assertEquals("0xF8DD", stringList.get(2));
        Assert.assertEquals("0xF8E1", stringList.get(3));
        Assert.assertEquals("0xF8E5", stringList.get(4));
        Assert.assertEquals("0xF8DA", stringList.get(5));
        Assert.assertEquals("0xF8DB", stringList.get(6));
        Assert.assertEquals("0xF8D2", stringList.get(7));

    }

    @Test
    public void translateWordWithGHInTheMiddleTest() throws InvalidDictionaryCharacterException, AlphabetNotFoundException {

        dictionaryService.setAlphabet(new KlingonAlphabet());
        List<String> stringList = dictionaryService.translate("ajorghumn");

        Assert.assertEquals(8, stringList.size());

        Assert.assertEquals("0xF8D0", stringList.get(0));
        Assert.assertEquals("0xF8D8", stringList.get(1));
        Assert.assertEquals("0xF8DD", stringList.get(2));
        Assert.assertEquals("0xF8E1", stringList.get(3));
        Assert.assertEquals("0xF8D5", stringList.get(4));
        Assert.assertEquals("0xF8E5", stringList.get(5));
        Assert.assertEquals("0xF8DA", stringList.get(6));
        Assert.assertEquals("0xF8DB", stringList.get(7));

    }

    @Test
    public void translateWordWithGHInTheEndTest() throws InvalidDictionaryCharacterException, AlphabetNotFoundException {

        dictionaryService.setAlphabet(new KlingonAlphabet());
        List<String> stringList = dictionaryService.translate("ajorumgh");

        Assert.assertEquals(7, stringList.size());

        Assert.assertEquals("0xF8D0", stringList.get(0));
        Assert.assertEquals("0xF8D8", stringList.get(1));
        Assert.assertEquals("0xF8DD", stringList.get(2));
        Assert.assertEquals("0xF8E1", stringList.get(3));
        Assert.assertEquals("0xF8E5", stringList.get(4));
        Assert.assertEquals("0xF8DA", stringList.get(5));
        Assert.assertEquals("0xF8D5", stringList.get(6));

    }

}
