package namegenerator.domain;

import java.util.ArrayList;

public class Name implements Comparable<Name> {
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
    public int compareTo(Name name) {
        return this.length() - name.length();
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
