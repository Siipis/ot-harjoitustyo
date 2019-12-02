package namegenerator.domain;

import namegenerator.domain.exceptions.LettersNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

public class Generator {

    private Language language;

    private Name name = new Name();

    private Random random = new Random();

    public Generator(Language language) {
        this.language = language;
    }

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

    private int pickLength() {
        int min = language.getMinLength();
        int max = language.getMaxLength();

        return random.nextInt((max - min) + 1) + min;
    }

    private Letter pickLetter() {
        ArrayList<Letter> letters = makeLetterList();

        if (letters.size() == 0) {
            return null;
        }

        int index = random.nextInt(letters.size());

        return letters.get(index);
    }

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

    private ArrayList<Letter> reverse(Name name) {
        ArrayList<Letter> reversed = new ArrayList<>(name.letters());

        Collections.reverse(reversed);

        return reversed;
    }
}
