package org.javaacadmey.wonder_field;

import java.util.Arrays;

public class Tableau {
    private static Tableau INSTANCE;
    char[] lettersOnTheTableau;
    char[] answerArray;

    private Tableau() {
    }

    public static Tableau getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Tableau();
        }
        return INSTANCE;
    }

    public void initTableau(String correctAnswer) {
        lettersOnTheTableau = new char[correctAnswer.length()];
        Arrays.fill(lettersOnTheTableau,'_');
        answerArray = correctAnswer.toUpperCase().toCharArray();
    }

    public void openLetter(char letter) {
        if (isAttributesFilled()) {
            for (int i = 0; i < answerArray.length; i++) {
                if (answerArray[i] == Character.toUpperCase(letter)) {
                    lettersOnTheTableau[i] = Character.toUpperCase(letter);
                }
            }
        }
    }

    public void openWholeWord() {
        for (int i = 0; i < answerArray.length; i++) {
            lettersOnTheTableau[i] = Character.toUpperCase(answerArray[i]);
        }
    }

    public void showTableau() {
        if (isAttributesFilled()) {
            String result = "";
            for (char element : lettersOnTheTableau) {
                result += element + " ";
            }
            System.out.println(result.trim());
        }
    }

    public boolean isAttributesFilled() {
        return answerArray != null && lettersOnTheTableau != null;
    }

    public boolean isContainUnknowletters() {
        boolean containUnknown = false;
        if (Arrays.toString(lettersOnTheTableau).contains("_")) {
            containUnknown = true;
        }
        return containUnknown;
    }
}
