package namegenerator.domain;

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
    public String toString() {
        return "" + character;
    }
}
