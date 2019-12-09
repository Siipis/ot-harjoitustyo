package namegenerator.domain;

import namegenerator.domain.exceptions.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LetterWeightTest {
    private Letter letter;
    private LetterWeight letterWeight;

    @Before
    public void setUp() throws IntegerOutOfBoundsException {
        letter = new Letter('a', LetterType.VOWEL);
        letterWeight = new LetterWeight(letter, 5);
    }

    @Test(expected = IntegerOutOfBoundsException.class)
    public void setWeightThrowsExceptionOnTooSmallValue() throws IntegerOutOfBoundsException {
        new LetterWeight(letter, -1);
    }

    @Test(expected = IntegerOutOfBoundsException.class)
    public void setWeightThrowsExceptionOnTooLargeValue() throws IntegerOutOfBoundsException {
        new LetterWeight(letter, 16);
    }

    @Test
    public void equalsReturnsFalseOnNull() {
        assertNotEquals(null, letterWeight);
    }

    @Test
    public void equalsReturnsTrueOnIdenticalWeights() throws IntegerOutOfBoundsException {
        LetterWeight w1 = new LetterWeight(letter, 2);

        assertEquals(w1, w1);
    }

    @Test
    public void equalsReturnsTrueOnDifferentWeight() throws IntegerOutOfBoundsException {
        LetterWeight w1 = new LetterWeight(letter, 2);
        LetterWeight w2 = new LetterWeight(letter, 5);

        assertEquals(w1, w2);
    }
}
