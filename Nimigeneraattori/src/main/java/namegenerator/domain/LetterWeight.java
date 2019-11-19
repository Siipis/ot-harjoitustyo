package namegenerator.domain;

import java.util.Objects;

public class LetterWeight {
    private Letter letter;
    private int weight;

    public LetterWeight(Letter letter, int weight) {
        this.letter = letter;
        this.weight = weight;
    }

    public Letter letter() {
        return letter;
    }

    public int weight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LetterWeight compared = (LetterWeight) o;
        return weight == compared.weight &&
                letter.equals(compared.letter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, weight);
    }
}