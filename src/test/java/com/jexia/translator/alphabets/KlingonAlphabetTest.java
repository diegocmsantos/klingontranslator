package com.jexia.translator.alphabets;

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
public class KlingonAlphabetTest {

    @Autowired
    Alphabet klingonAlphabet;

    @Test
    public void translateTestUhura() throws InvalidDictionaryCharacterException {

        List<String> stringList = klingonAlphabet.translate("uhura");

        Assert.assertEquals(5, stringList.size());

        Assert.assertEquals("0xF8E5", stringList.get(0));
        Assert.assertEquals("0xF8D6", stringList.get(1));
        Assert.assertEquals("0xF8E5", stringList.get(2));
        Assert.assertEquals("0xF8E1", stringList.get(3));
        Assert.assertEquals("0xF8D0", stringList.get(4));

    }

    @Test
    public void translateTestMinra() throws InvalidDictionaryCharacterException {

        List<String> stringList = klingonAlphabet.translate("minra");

        Assert.assertEquals(5, stringList.size());

        Assert.assertEquals("0xF8DA", stringList.get(0));
        Assert.assertEquals("0xF8D7", stringList.get(1));
        Assert.assertEquals("0xF8DB", stringList.get(2));
        Assert.assertEquals("0xF8E1", stringList.get(3));
        Assert.assertEquals("0xF8D0", stringList.get(4));

    }

    @Test(expected = InvalidDictionaryCharacterException.class)
    public void translateTestGreatMinra() throws InvalidDictionaryCharacterException {

        List<String> stringList = klingonAlphabet.translate("great minra");

    }

    @Test
    public void translateTestEmpty() throws InvalidDictionaryCharacterException {

        List<String> stringList = klingonAlphabet.translate("");
        Assert.assertEquals(0, stringList.size());

    }

}
