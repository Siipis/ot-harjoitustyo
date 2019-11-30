package namegenerator;

import namegenerator.domain.Language;
import namegenerator.domain.exceptions.IntegerOutOfBoundsException;
import org.junit.Before;
import org.junit.Test;

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
}
