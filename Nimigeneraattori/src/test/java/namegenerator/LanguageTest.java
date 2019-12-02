package namegenerator;

import namegenerator.domain.*;
import namegenerator.domain.exceptions.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LanguageTest {
    Language language;

    @Before
    public void setUp() throws Exception {
        this.language = new Language();
        language.setMinLength(5);
        language.setMaxLength(10);
    }

    @Test(expected = IntegerOutOfBoundsException.class)
    public void setMinLengthThrowsExceptionOnNegativeValue() throws IntegerOutOfBoundsException {
        language.setMinLength(-1);
    }

    @Test(expected = IntegerOutOfBoundsException.class)
    public void setMinLengthThrowsExceptionOnTooLargeValue() throws IntegerOutOfBoundsException {
        language.setMinLength(11);
    }

    @Test(expected = IntegerOutOfBoundsException.class)
    public void setMaxLengthThrowsExceptionOnNegativeValue() throws IntegerOutOfBoundsException {
        language.setMaxLength(-1);
    }

    @Test(expected = IntegerOutOfBoundsException.class)
    public void setMaxLengthThrowsExceptionOnTooSmallValue() throws IntegerOutOfBoundsException {
        language.setMaxLength(4);
    }

    @Test(expected = IntegerOutOfBoundsException.class)
    public void setVowelGroupSizeThrowsExceptionOnTooSmallValue() throws IntegerOutOfBoundsException {
        language.setVowelGroupSize(0);
    }

    @Test(expected = IntegerOutOfBoundsException.class)
    public void setConsonantGroupSizeThrowsExceptionOnTooSmallValue() throws IntegerOutOfBoundsException {
        language.setConsonantGroupSize(0);
    }

    @Test
    public void addLetterRejectsDuplicates() throws IntegerOutOfBoundsException {
        language.addLetter(new Letter('a', LetterType.VOWEL), 1);
        language.addLetter(new Letter('a', LetterType.VOWEL), 1);

        assertEquals(1, language.letters().size());
    }

    @Test
    public void highestWeightReturnsCorrectValue() throws IntegerOutOfBoundsException {
        language.addLetter(new Letter('a', LetterType.VOWEL), 1);
        language.addLetter(new Letter('b', LetterType.VOWEL), 3);
        language.addLetter(new Letter('d', LetterType.VOWEL), 5);
        language.addLetter(new Letter('c', LetterType.VOWEL), 3);

        assertEquals(5, language.highestWeight());
    }
}
