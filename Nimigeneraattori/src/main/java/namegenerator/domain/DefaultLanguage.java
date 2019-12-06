package namegenerator.domain;

/**
 * Default language configuration.
 */
public class DefaultLanguage extends Language {
    public DefaultLanguage() {
        try {
            this.setName("English");

            this.addLetter(new Letter('a', LetterType.VOWEL), 8);
            this.addLetter(new Letter('b', LetterType.CONSONANT), 2);
            this.addLetter(new Letter('c', LetterType.CONSONANT), 3);
            this.addLetter(new Letter('d', LetterType.CONSONANT), 4);
            this.addLetter(new Letter('e', LetterType.VOWEL), 12);
            this.addLetter(new Letter('f', LetterType.CONSONANT), 2);
            this.addLetter(new Letter('g', LetterType.CONSONANT), 2);
            this.addLetter(new Letter('h', LetterType.CONSONANT), 6);
            this.addLetter(new Letter('i', LetterType.VOWEL), 7);
            this.addLetter(new Letter('j', LetterType.CONSONANT), 1);
            this.addLetter(new Letter('k', LetterType.CONSONANT), 1);
            this.addLetter(new Letter('l', LetterType.CONSONANT), 4);
            this.addLetter(new Letter('m', LetterType.CONSONANT), 3);
            this.addLetter(new Letter('n', LetterType.CONSONANT), 7);
            this.addLetter(new Letter('o', LetterType.VOWEL), 8);
            this.addLetter(new Letter('p', LetterType.CONSONANT), 2);
            this.addLetter(new Letter('q', LetterType.CONSONANT), 0);
            this.addLetter(new Letter('r', LetterType.CONSONANT), 6);
            this.addLetter(new Letter('s', LetterType.CONSONANT), 6);
            this.addLetter(new Letter('t', LetterType.CONSONANT), 9);
            this.addLetter(new Letter('u', LetterType.VOWEL), 3);
            this.addLetter(new Letter('v', LetterType.CONSONANT), 1);
            this.addLetter(new Letter('w', LetterType.CONSONANT), 2);
            this.addLetter(new Letter('x', LetterType.CONSONANT), 0);
            this.addLetter(new Letter('y', LetterType.BOTH), 2);
            this.addLetter(new Letter('z', LetterType.CONSONANT), 0);

            this.setMinLength(3);
            this.setMaxLength(8);

            this.setVowelGroupSize(2);
            this.setDoubleVowels(true);
            this.setConsonantGroupSize(2);
            this.setDoubleConsonants(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
