package namegenerator.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class LetterTest {

    @Test
    public void equalsReturnsTrueOnIdenticalLetters() {
        Letter l1 = new Letter('a', LetterType.VOWEL);
        Letter l2 = new Letter('a', LetterType.VOWEL);

        assertEquals(l1, l2);
    }

    @Test
    public void equalsReturnsFalseOnDivergingLetterType() {
        Letter l1 = new Letter('y', LetterType.VOWEL);
        Letter l2 = new Letter('y', LetterType.CONSONANT);

        assertNotEquals(l1, l2);
    }
}
