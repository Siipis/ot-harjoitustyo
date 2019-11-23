package namegenerator.domain;

import namegenerator.domain.exceptions.NameLengthException;

import java.util.ArrayList;

public class Language {
    private ArrayList<LetterWeight> letters;
    private int minLength;
    private int maxLength;
    private int vowelGroupSize;
    private int consonantGroupSize;
    private boolean doubleVowels;
    private boolean doubleConsonants;

    public Language() {
        this.letters = new ArrayList<>();
        this.minLength = 3;
        this.maxLength = 12;
        this.vowelGroupSize = 2;
        this.consonantGroupSize = 2;
        this.doubleVowels = true;
        this.doubleConsonants = true;
    }

    /**
     * =========================================
     *
     * BUSINESS LOGIC
     *
     * =========================================
     */

    public void addLetter(Letter letter, int weight) {
        LetterWeight letterWeight = new LetterWeight(letter, weight);

        if (this.letters.contains(letterWeight)) {
            return;
        }
        this.letters.add(letterWeight);
    }

    public void removeLetter(Letter letter) {
        LetterWeight weight = this.findByLetter(letter);
        this.letters.remove(weight);
    }

    /**
     * =========================================
     *
     * SETTERS AND GETTERS
     *
     * =========================================
     */

    public ArrayList<LetterWeight> letters() {
        return this.letters;
    }

    public void setLetters(ArrayList<LetterWeight> letters) {
        this.letters = letters;
    }

    private LetterWeight findByLetter(Letter letter) {
        for (LetterWeight weight : this.letters) {
            if (weight.letter().equals(letter)) {
                return weight;
            }
        }

        return null;
    }

    public int getMinLength() {
        return this.minLength;
    }

    public void setMinLength(int minLength) throws NameLengthException {
        if (minLength <= 0) {
            throw new NameLengthException("Min length must be a positive integer.");
        }

        if (minLength > this.maxLength) {
            throw new NameLengthException("Min length can't be higher than max length.");
        }

        this.minLength = minLength;
    }

    public int getMaxLength() {
        return this.maxLength;
    }

    public void setMaxLength(int maxLength) throws NameLengthException {
        if (maxLength <= 0) {
            throw new NameLengthException("Max length must be a positive integer.");
        }

        if (maxLength < this.minLength) {
            throw new NameLengthException("Max length can't be lower than min length.");
        }

        this.maxLength = maxLength;
    }

    public int getVowelGroupSize() {
        return this.vowelGroupSize;
    }

    public void setVowelGroupSize(int vowelGroupSize) {
        this.vowelGroupSize = vowelGroupSize;
    }

    public int getConsonantGroupSize() {
        return this.consonantGroupSize;
    }

    public void setConsonantGroupSize(int consonantGroupSize) {
        this.consonantGroupSize = consonantGroupSize;
    }

    public boolean hasDoubleVowels() {
        return doubleVowels;
    }

    public void setDoubleVowels(boolean doubleVowels) {
        this.doubleVowels = doubleVowels;
    }

    public boolean hasDoubleConsonants() {
        return doubleConsonants;
    }

    public void setDoubleConsonants(boolean doubleConsonants) {
        this.doubleConsonants = doubleConsonants;
    }
}
