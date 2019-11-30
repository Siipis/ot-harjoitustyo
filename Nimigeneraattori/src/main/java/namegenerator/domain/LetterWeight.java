package namegenerator.domain;

import java.util.Objects;

public class LetterWeight {
    private static int maxWeight = 15;
    private static int minWeight = 0;

    private Letter letter;
    private int weight;

    public LetterWeight(Letter letter, int weight) {
        this.letter = letter;
        this.setWeight(weight);
    }

    public Letter letter() {
        return letter;
    }

    public int weight() {
        return weight;
    }

    public void setWeight(int weight) throws IndexOutOfBoundsException {
        if (weight > maxWeight) {
            throw new IndexOutOfBoundsException("Letter weight can't be higher than " + maxWeight);
        }

        this.weight = weight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getMinWeight() {
        return minWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LetterWeight compared = (LetterWeight) o;
        return weight == compared.weight &&
            letter.equals(compared.letter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, weight);
    }
}
