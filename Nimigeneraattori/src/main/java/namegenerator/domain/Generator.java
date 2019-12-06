package namegenerator.domain;

import namegenerator.domain.exceptions.*;

import java.util.*;

/**
 * Random name generator.
 */
public class Generator {

    private Language language;

    private Name name = new Name();

    private Random random = new Random();

    /**
     * Constructs a Generator object using a given language.
     * @param language
     */
    public Generator(Language language) {
        this.language = language;
    }

    /**
     * Generates a random name.
     *
     * @return random name
     * @throws LettersNotFoundException if the language contains no letters
     */
    public Name generate() throws LettersNotFoundException {
        if (language.letters().size() == 0) {
            throw new LettersNotFoundException();
        }

        name = new Name();

        while (name.length() < this.pickLength()) {
            Letter letter = this.pickLetter();

            if (letter == null) {
                break;
            }

            name.addLetter(letter);
        }

        return name;
    }

    /**
     * Picks a name length to generate.
     * @return random length
     */
    private int pickLength() {
        int min = language.getMinLength();
        int max = language.getMaxLength();

        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Picks a letter from a letter list.
     *
     * @return random letter
     */
    private Letter pickLetter() {
        ArrayList<Letter> letters = makeLetterList();

        if (letters.size() == 0) {
            return null;
        }

        int index = random.nextInt(letters.size());

        return letters.get(index);
    }

    /**
     * Constructs a list of all available letters.
     *
     * @return list of letters
     */
    public ArrayList<Letter> makeLetterList() {
        ArrayList<Letter> letters = new ArrayList<>();

        for (LetterWeight w : language.letters()) {
            if (this.letterIsValid(w)) {
                for (int i = 0; i < w.weight(); i++) {
                    letters.add(w.letter());
                }
            }
        }

        return letters;
    }

    /**
     * Checks that a letter can be picked.
     *
     * @param weight letter to check
     * @return true if the letter is valid
     */
    private boolean letterIsValid(LetterWeight weight) {
        Letter letter = weight.letter();

        int maxExactMatches = Integer.MAX_VALUE;
        int maxTypeMatches = Integer.MAX_VALUE;

        if (letter.getType() == LetterType.BOTH) {
            maxExactMatches = language.hasDoubleVowels() || language.hasDoubleConsonants() ? 2 : 1;
            maxTypeMatches = Integer.max(language.getVowelGroupSize(), language.getConsonantGroupSize());
        }

        if (letter.getType() == LetterType.CONSONANT) {
            maxExactMatches = language.hasDoubleConsonants() ? 2 : 1;
            maxTypeMatches = language.getConsonantGroupSize();
        }

        if (letter.getType() == LetterType.VOWEL) {
            maxExactMatches = language.hasDoubleVowels() ? 2 : 1;
            maxTypeMatches = language.getVowelGroupSize();
        }

        return countExactMatches(letter) < maxExactMatches && countTypeMatches(letter) < maxTypeMatches;
    }

    /**
     * Counts how many times the letter occurs at the end of the name.
     *
     * @param letter letter to check
     * @return number of occurrences
     */
    private int countExactMatches(Letter letter) {
        int count = 0;

        for (Letter l : this.reverse(name)) {
            if (l.equals(letter)) {
                count++;
            } else {
                return count;
            }
        }

        return count;
    }

    /**
     * Counts how many times the letter type occurs at the end of the name.
     *
     * @param letter letter to check
     * @return number of occurrences
     */
    private int countTypeMatches(Letter letter) {
        int count = 0;

        for (Letter l : this.reverse(name)) {
            if (l.getType() == letter.getType()) {
                count++;
            } else {
                return count;
            }
        }

        return count;
    }

    /**
     * Reverses the name for easier lookup.
     */
    private ArrayList<Letter> reverse(Name name) {
        ArrayList<Letter> reversed = new ArrayList<>(name.letters());

        Collections.reverse(reversed);

        return reversed;
    }
}
