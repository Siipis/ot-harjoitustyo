package namegenerator;

import namegenerator.domain.*;
import namegenerator.domain.exceptions.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LetterWeightTest {
    private Letter letter;

    @Before
    public void setUp() {
        letter = new Letter('a', LetterType.VOWEL);
    }

    @Test(expected = IntegerOutOfBoundsException.class)
    public void setWeightThrowsExceptionOnTooSmallValue() throws IntegerOutOfBoundsException {
        new LetterWeight(letter, -1);
    }

    @Test(expected = IntegerOutOfBoundsException.class)
    public void setWeightThrowsExceptionOnTooLargeValue() throws IntegerOutOfBoundsException {
        new LetterWeight(letter, 16);
    }
}
