package namegenerator;

import namegenerator.domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Language language = new Language();
        language.addLetter(new Letter(LetterType.VOWEL, 'a'), 1);
        language.addLetter(new Letter(LetterType.VOWEL, 'e'), 1);
        language.addLetter(new Letter(LetterType.VOWEL, 'i'), 1);
        language.addLetter(new Letter(LetterType.CONSONANT, 'k'), 1);
        language.addLetter(new Letter(LetterType.CONSONANT, 't'), 1);
        language.addLetter(new Letter(LetterType.CONSONANT, 'n'), 1);

        Generator generator = new Generator();

        Scanner scanner = new Scanner(System.in);
        System.out.print("How many names do you want to generate? ");
        int count = scanner.nextInt();

        ArrayList<Name> names = new ArrayList<>();

        try {
            for (int i = 0; i < count; i++) {
                names.add(generator.generate(language));
            }
        } catch (LettersNotFoundException e) {
            System.out.println(e.getMessage());
        }

        for (Name name : names) {
            System.out.println(name);
        }

        System.out.println("\n==============================================\n");
        System.out.println("Generated " + names.size() + " names.");

        Collections.sort(names);

        System.out.println("Longest name: " + names.get(names.size() - 1) + ", length: " + names.get(names.size() - 1).length());
        System.out.println("Shortest name: " + names.get(0) + ", length: " + names.get(0).length());
    }
}
