package org.javaacadmey.wonder_field.player;

public class PlayerAnswer {
    char answerType;
    String answerContent;
    char answerContentChar;

    public char getAnswerType() {
        return answerType;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public char getAnswerContentChar() {
        return answerContentChar;
    }

    public PlayerAnswer(char answerType, String answerContent) {
        this.answerType = answerType;
        this.answerContent = answerContent;
    }

    public PlayerAnswer(char answerType, char answerContentChar) {
        this.answerType = answerType;
        this.answerContentChar = answerContentChar;
    }
}
