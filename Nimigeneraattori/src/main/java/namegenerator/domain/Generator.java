package namegenerator.domain;

import java.util.Random;

public class Generator {

    private Random random = new Random();

    public Name generate(Language language) throws LettersNotFoundException {
        if (language.letters().size() == 0) {
            throw new LettersNotFoundException();
        }

        Name name = new Name();

        for (int i = 0; i < this.pickLength(language); i++) {
            name.addLetter(this.pickLetter(language, name));
        }

        return name;
    }

    private int pickLength(Language language) {
        int min = language.getMinLength();
        int max = language.getMaxLength();

        return random.nextInt((max - min) + 1) + min;
    }

    private Letter pickLetter(Language language, Name name) {
        // TODO: use existing letters to influence choice
        // TODO: use letter weights to influence choice
        // TODO: use language configuration to influence letter grouping

        int index = random.nextInt(language.letters().size());

        return language.letters().get(index).letter();
    }
}
