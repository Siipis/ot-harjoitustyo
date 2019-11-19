package namegenerator.domain;

public class Letter {
    private LetterType type;
    private Character character;

    public Letter(LetterType type, Character character) {
        this.type = type;
        this.character = character;
    }

    @Override
    public String toString() {
        return "" + character;
    }
}
