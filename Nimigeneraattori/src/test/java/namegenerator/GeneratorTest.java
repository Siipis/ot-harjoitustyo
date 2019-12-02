package namegenerator;

import namegenerator.domain.*;
import namegenerator.domain.exceptions.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GeneratorTest {

    private Generator generator;
    private Language language;

    private int sampleSize = 10000;

    @Before
    public void setUp() throws IntegerOutOfBoundsException {
        language = new Language();
        language.addLetter(new Letter('a', LetterType.VOWEL), 1);
        language.addLetter(new Letter('e', LetterType.VOWEL), 1);
        language.addLetter(new Letter('i', LetterType.VOWEL), 5);
        language.addLetter(new Letter('k', LetterType.CONSONANT), 1);
        language.addLetter(new Letter('t', LetterType.CONSONANT), 1);
        language.addLetter(new Letter('n', LetterType.CONSONANT), 3);

        generator = new Generator(language);
    }

    @Test(expected = LettersNotFoundException.class)
    public void throwsExceptionOnEmptyLanguage() throws LettersNotFoundException {
        generator = new Generator(new Language());

        generator.generate();
    }

    @Test
    public void generatesValidLength() throws Exception {
        language.setMinLength(5);
        language.setMaxLength(5);

        assertEquals(5, generator.generate().toString().length());
    }

    @Test
    public void generatesValidMinLength() throws Exception {
        language.setMinLength(3);
        language.setMaxLength(10);

        int shortest = Integer.MAX_VALUE;
        for (int i = 1; i <= sampleSize; i++) {
            Name name = generator.generate();

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
            Name name = generator.generate();

            if (name.length() > longest) {
                longest = name.length();
            }
        }

        assertEquals(10, longest);
    }

    @Test
    public void generatesValidLetterList() throws IntegerOutOfBoundsException {
        language = new Language();
        language.addLetter(new Letter('a', LetterType.VOWEL), 1);
        language.addLetter(new Letter('e', LetterType.VOWEL), 3);
        language.addLetter(new Letter('i', LetterType.VOWEL), 2);

        generator = new Generator(language);

        ArrayList<Letter> letterList = generator.makeLetterList();

        assertEquals(6, letterList.size());
        assertEquals("a", letterList.get(0).toString());
        assertEquals("e", letterList.get(1).toString());
        assertEquals("e", letterList.get(3).toString());
        assertEquals("i", letterList.get(4).toString());
    }

    @Test
    public void validatorAllowsValidDoubleVowels() throws IntegerOutOfBoundsException, LettersNotFoundException {
        Language language = new Language();
        language.setDoubleVowels(true);
        language.setMinLength(2);
        language.setMaxLength(2);

        language.addLetter(new Letter('a', LetterType.VOWEL), 1);

        Generator generator = new Generator(language);

        assertEquals("aa", generator.generate().toString());
    }

    @Test
    public void validatorRejectsDisallowedDoubleVowels() throws IntegerOutOfBoundsException, LettersNotFoundException {
        Language language = new Language();
        language.setDoubleVowels(false);
        language.setMinLength(2);
        language.setMaxLength(2);

        language.addLetter(new Letter('a', LetterType.VOWEL), 1);

        Generator generator = new Generator(language);

        assertEquals("a", generator.generate().toString());
    }

    @Test
    public void validatorAllowsValidDoubleConsonants() throws IntegerOutOfBoundsException, LettersNotFoundException {
        Language language = new Language();
        language.setDoubleConsonants(true);
        language.setMinLength(2);
        language.setMaxLength(2);

        language.addLetter(new Letter('b', LetterType.CONSONANT), 1);

        Generator generator = new Generator(language);

        assertEquals("bb", generator.generate().toString());
    }

    @Test
    public void validatorRejectsDisallowedDoubleConsonants() throws IntegerOutOfBoundsException, LettersNotFoundException {
        Language language = new Language();
        language.setDoubleConsonants(false);
        language.setMinLength(2);
        language.setMaxLength(2);

        language.addLetter(new Letter('b', LetterType.CONSONANT), 1);

        Generator generator = new Generator(language);

        assertEquals("b", generator.generate().toString());
    }

    @Test
    public void validatorAcceptsValidVowelGroups() throws IntegerOutOfBoundsException, LettersNotFoundException {
        Language language = new Language();
        language.setDoubleVowels(false);
        language.setVowelGroupSize(2);
        language.setMinLength(2);
        language.setMaxLength(2);

        language.addLetter(new Letter('a', LetterType.VOWEL), 1);
        language.addLetter(new Letter('o', LetterType.VOWEL), 1);

        Generator generator = new Generator(language);
        Name name = generator.generate();

        assertTrue(name.toString().equals("ao") || name.toString().equals("oa"));
    }

    @Test
    public void validatorRejectsTooLargeVowelGroups() throws IntegerOutOfBoundsException, LettersNotFoundException {
        Language language = new Language();
        language.setDoubleVowels(false);
        language.setVowelGroupSize(2);
        language.setMinLength(3);
        language.setMaxLength(3);

        language.addLetter(new Letter('a', LetterType.VOWEL), 1);
        language.addLetter(new Letter('o', LetterType.VOWEL), 1);

        Generator generator = new Generator(language);
        Name name = generator.generate();

        assertTrue(name.toString().equals("ao") || name.toString().equals("oa"));
    }

    @Test
    public void validatorAcceptsValidConsonantGroups() throws IntegerOutOfBoundsException, LettersNotFoundException {
        Language language = new Language();
        language.setDoubleConsonants(false);
        language.setConsonantGroupSize(2);
        language.setMinLength(2);
        language.setMaxLength(2);

        language.addLetter(new Letter('b', LetterType.CONSONANT), 1);
        language.addLetter(new Letter('z', LetterType.CONSONANT), 1);

        Generator generator = new Generator(language);
        Name name = generator.generate();

        assertTrue(name.toString().equals("bz") || name.toString().equals("zb"));
    }

    @Test
    public void validatorRejectsTooLargeConsonantGroups() throws IntegerOutOfBoundsException, LettersNotFoundException {
        Language language = new Language();
        language.setDoubleConsonants(false);
        language.setConsonantGroupSize(2);
        language.setMinLength(3);
        language.setMaxLength(3);

        language.addLetter(new Letter('b', LetterType.CONSONANT), 1);
        language.addLetter(new Letter('z', LetterType.CONSONANT), 1);

        Generator generator = new Generator(language);
        Name name = generator.generate();

        assertTrue(name.toString().equals("bz") || name.toString().equals("zb"));
    }
}
