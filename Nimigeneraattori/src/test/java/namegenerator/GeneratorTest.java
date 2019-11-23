package namegenerator;

import namegenerator.domain.*;
import namegenerator.domain.exceptions.LettersNotFoundException;
import namegenerator.domain.exceptions.NameLengthException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GeneratorTest {

    private Generator generator;
    private Language language;

    private int sampleSize = 10000;

    public GeneratorTest() {
        generator = new Generator();
    }
    
    @Before
    public void setUp() {
        language = new Language();
        language.addLetter(new Letter(LetterType.VOWEL, 'a'), 1);
        language.addLetter(new Letter(LetterType.VOWEL, 'e'), 1);
        language.addLetter(new Letter(LetterType.VOWEL, 'i'), 5);
        language.addLetter(new Letter(LetterType.CONSONANT, 'k'), 1);
        language.addLetter(new Letter(LetterType.CONSONANT, 't'), 1);
        language.addLetter(new Letter(LetterType.CONSONANT, 'n'), 3);
    }

    @Test(expected = LettersNotFoundException.class)
    public void throwsExceptionOnEmptyLanguage() throws LettersNotFoundException {
        Language language = new Language();

        generator.generate(language);
    }

    @Test
    public void generatesValidLength() throws Exception {
        language.setMinLength(5);
        language.setMaxLength(5);

        assertEquals(5, generator.generate(language).toString().length());
    }

    @Test
    public void generatesValidMinLength() throws Exception {
        language.setMinLength(3);
        language.setMaxLength(10);

        int shortest = Integer.MAX_VALUE;
        for (int i = 1; i <= sampleSize; i++) {
            Name name = generator.generate(language);

            if (name.length() < shortest) {
                shortest = name.length();
            }
        }

        assertEquals(3, shortest);
    }

    @Test
    public void generatesValidMaxLength() throws Exception {
        language.setMinLength(3);
        language.setMaxLength(10);

        int longest = Integer.MIN_VALUE;
        for (int i = 1; i <= sampleSize; i++) {
            Name name = generator.generate(language);

            if (name.length() > longest) {
                longest = name.length();
            }
        }

        assertEquals(10, longest);
    }

    @Test
    public void generatesValidLetterList() {
        language = new Language();
        language.addLetter(new Letter(LetterType.VOWEL, 'a'), 1);
        language.addLetter(new Letter(LetterType.VOWEL, 'e'), 3);
        language.addLetter(new Letter(LetterType.VOWEL, 'i'), 2);

        ArrayList<Letter> letterList = generator.makeLetterList(language);

        assertEquals(6, letterList.size());
        assertEquals("a", letterList.get(0).toString());
        assertEquals("e", letterList.get(1).toString());
        assertEquals("e", letterList.get(3).toString());
        assertEquals("i", letterList.get(4).toString());
    }
}
