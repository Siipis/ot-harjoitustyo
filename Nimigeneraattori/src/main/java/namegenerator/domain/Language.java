package namegenerator.domain;

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

    public int getWeight(Letter letter) {
        LetterWeight weight = this.findByLetter(letter);

        if (weight == null) {
            return 0;
        }
        return weight.weight();
    }

    public void setWeight(Letter letter, int weight) {
        LetterWeight letterWeight = this.findByLetter(letter);

        if (letterWeight == null) {
            return;
        }
        letterWeight.setWeight(weight);
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

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return this.maxLength;
    }

    public void setMaxLength(int maxLength) {
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
