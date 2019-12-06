package namegenerator.domain;

import java.util.ArrayList;

/**
 * Name data.
 */
public class Name {
    private ArrayList<Letter> letters = new ArrayList<>();

    public void addLetter(Letter letter) {
        this.letters.add(letter);
    }

    public ArrayList<Letter> letters() {
        return letters;
    }

    public int length() {
        return this.letters.size();
    }

    @Override
    public String toString() {
        String name = "";

        for (Letter letter : this.letters) {
            name += letter;
        }

        return name;
    }
}
