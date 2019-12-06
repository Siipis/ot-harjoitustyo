package namegenerator.domain;

import namegenerator.domain.exceptions.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LanguageTest {
    private Language language;

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

    @Test
    public void equalsReturnsTrueOnIdenticalLanguages() {
        Language l1 = new Language();
        Language l2 = new Language();

        assertEquals(l1, l2);
    }

    @Test
    public void equalsReturnsFalseOnNull() {
        assertNotEquals(null, new Language());
    }

    @Test
    public void equalsReturnsFalseOnDivergingMinLength() throws IntegerOutOfBoundsException {
        Language l1 = new Language();
        l1.setMinLength(2);

        Language l2 = new Language();
        l2.setMinLength(3);

        assertNotEquals(l1, l2);
    }

    @Test
    public void equalsReturnsFalseOnDivergingMaxLength() throws IntegerOutOfBoundsException {
        Language l1 = new Language();
        l1.setMaxLength(7);

        Language l2 = new Language();
        l2.setMaxLength(8);

        assertNotEquals(l1, l2);
    }

    @Test
    public void equalsReturnsFalseOnDivergingConsonantGroupSize() throws IntegerOutOfBoundsException {
        Language l1 = new Language();
        l1.setConsonantGroupSize(2);

        Language l2 = new Language();
        l2.setConsonantGroupSize(3);

        assertNotEquals(l1, l2);
    }

    @Test
    public void equalsReturnsFalseOnDivergingVowelGroupSize() throws IntegerOutOfBoundsException {
        Language l1 = new Language();
        l1.setVowelGroupSize(2);

        Language l2 = new Language();
        l2.setVowelGroupSize(3);

        assertNotEquals(l1, l2);
    }

    @Test
    public void equalsReturnsFalseOnDivergingDoubleConsonants() {
        Language l1 = new Language();
        l1.setDoubleConsonants(true);

        Language l2 = new Language();
        l2.setDoubleConsonants(false);

        assertNotEquals(l1, l2);
    }

    @Test
    public void equalsReturnsFalseOnDivergingDoubleVowels() {
        Language l1 = new Language();
        l1.setDoubleVowels(true);

        Language l2 = new Language();
        l2.setDoubleVowels(false);

        assertNotEquals(l1, l2);
    }

    @Test
    public void equalsReturnsTrueOnMatchingLetters() throws IntegerOutOfBoundsException {
        Language l1 = new Language();
        l1.addLetter(new Letter('a', LetterType.VOWEL), 1);

        Language l2 = new Language();
        l2.addLetter(new Letter('a', LetterType.VOWEL), 1);

        assertEquals(l1, l2);
    }

    @Test
    public void equalsReturnsFalseOnDivergingLetters() throws IntegerOutOfBoundsException {
        Language l1 = new Language();
        l1.addLetter(new Letter('a', LetterType.VOWEL), 1);

        Language l2 = new Language();
        l2.addLetter(new Letter('b', LetterType.CONSONANT), 1);

        assertNotEquals(l1, l2);
    }
}
