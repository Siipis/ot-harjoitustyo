package namegenerator;

import namegenerator.domain.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        language.addLetter(new Letter(LetterType.VOWEL, 'i'), 1);
        language.addLetter(new Letter(LetterType.CONSONANT, 'k'), 1);
        language.addLetter(new Letter(LetterType.CONSONANT, 't'), 1);
        language.addLetter(new Letter(LetterType.CONSONANT, 'n'), 1);
    }

    @Test(expected = LettersNotFoundException.class)
    public void throwsExceptionOnEmptyLanguage() throws LettersNotFoundException {
        Language language = new Language();

        generator.generate(language);
    }

    @Test
    public void generatesValidLength() throws LettersNotFoundException {
        language.setMinLength(5);
        language.setMaxLength(5);

        assertEquals(5, generator.generate(language).length());
    }

    @Test
    public void generatesValidMinLength() throws LettersNotFoundException {
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
    public void generatesValidMaxLength() throws LettersNotFoundException {
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
}