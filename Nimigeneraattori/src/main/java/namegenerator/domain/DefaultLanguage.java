package namegenerator.domain;

public class DefaultLanguage extends Language {
    public DefaultLanguage() {
        int weight = 5;
        this.addLetter(new Letter(LetterType.VOWEL, 'a'), weight);
        this.addLetter(new Letter(LetterType.VOWEL, 'e'), weight);
        this.addLetter(new Letter(LetterType.VOWEL, 'i'), weight);
        this.addLetter(new Letter(LetterType.CONSONANT, 'k'), weight);
        this.addLetter(new Letter(LetterType.CONSONANT, 't'), weight);
        this.addLetter(new Letter(LetterType.CONSONANT, 'n'), weight);
    }
}
