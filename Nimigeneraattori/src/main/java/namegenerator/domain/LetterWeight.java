package namegenerator.domain;

import namegenerator.domain.exceptions.*;

/**
 * Letter weight model.
 */
public class LetterWeight {
    private static int maxWeight = 15;
    private static int minWeight = 0;

    private Letter letter;
    private int weight;

    public LetterWeight(Letter letter, int weight) throws IntegerOutOfBoundsException {
        this.letter = letter;
        this.setWeight(weight);
    }

    public Letter letter() {
        return letter;
    }

    public int weight() {
        return weight;
    }

    public void setWeight(int weight) throws IntegerOutOfBoundsException {
        if (weight > maxWeight) {
            throw new IntegerOutOfBoundsException("Letter weight can't be higher than " + maxWeight);
        }

        if (weight < minWeight) {
            throw new IntegerOutOfBoundsException("Letter weight can't be lower than " + minWeight);
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
        return letter.equals(compared.letter);
    }
}
