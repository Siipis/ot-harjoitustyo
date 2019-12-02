package namegenerator.domain;

import java.util.Objects;

public class Letter {
    private Character character;
    private LetterType type;

    public Letter(Character character, LetterType type) {
        this.character = character;
        this.type = type;
    }

    public LetterType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Letter letter = (Letter) o;
        return character.equals(letter.character) &&
            type == letter.type;
    }

    @Override
    public String toString() {
        return "" + character;
    }
}
