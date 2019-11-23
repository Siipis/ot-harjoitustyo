package namegenerator;

import namegenerator.domain.Language;
import namegenerator.domain.exceptions.NameLengthException;
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

    @Test(expected = NameLengthException.class)
    public void setMinLengthThrowsExceptionOnNegativeValue() throws NameLengthException {
        language.setMinLength(-1);
    }

    @Test(expected = NameLengthException.class)
    public void setMinLengthThrowsExceptionOnTooLargeValue() throws NameLengthException {
        language.setMinLength(11);
    }

    @Test(expected = NameLengthException.class)
    public void setMaxLengthThrowsExceptionOnNegativeValue() throws NameLengthException {
        language.setMaxLength(-1);
    }

    @Test(expected = NameLengthException.class)
    public void setMaxLengthThrowsExceptionOnTooSmallValue() throws NameLengthException {
        language.setMaxLength(4);
    }
}
