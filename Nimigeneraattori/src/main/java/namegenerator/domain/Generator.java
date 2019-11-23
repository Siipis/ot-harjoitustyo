package namegenerator.domain;

import namegenerator.domain.exceptions.LettersNotFoundException;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

    private Random random = new Random();

    public Name generate(Language language) throws LettersNotFoundException {
        if (language.letters().size() == 0) {
            throw new LettersNotFoundException();
        }

        Name name = new Name();

        while (name.length() < this.pickLength(language)) {
            Letter letter = this.pickLetter(language, name);

            if (letterIsValid(letter)) {
                name.addLetter(letter);
            }
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
        // TODO: use language configuration to influence letter grouping

        ArrayList<Letter> letters = makeLetterList(language);

        int index = random.nextInt(letters.size());

        return letters.get(index);
    }

    private boolean letterIsValid(Letter letter) {
        return true;
    }

    public ArrayList<Letter> makeLetterList(Language language) {
        ArrayList<Letter> letters = new ArrayList<>();

        for (LetterWeight w : language.letters()) {
            for (int i = 0; i < w.weight(); i++) {
                letters.add(w.letter());
            }
        }

        return letters;
    }
}
