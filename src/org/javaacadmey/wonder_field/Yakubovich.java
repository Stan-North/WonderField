package org.javaacadmey.wonder_field;

import org.javaacadmey.wonder_field.player.Player;
import org.javaacadmey.wonder_field.player.PlayerAnswer;

import java.util.ArrayList;

public class Yakubovich {

    private static Yakubovich INSTANCE;

    private Yakubovich() {
    }

    public static Yakubovich getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Yakubovich();
        }
        return INSTANCE;
    }

    public void startGame() {
        System.out.println("Якубович: Здравствуйте, уважаемые дамы и господа! Пятница! В эфире капитал-шоу «Поле чудес»!");
    }

    public void endGame() {
        System.out.println("Якубович: Мы прощаемся с вами ровно на одну неделю! Здоровья вам, до встречи!");
    }

    public void endGameWithoutWinner() {
        System.out.println("Якубович: на этой неделе победителя нет!");
    }

    public void invitePlayers(Player[] players, int roundNumber) {
        if (roundNumber != 3) {
            System.out.println(String.format("Якубович: приглашаю %d тройку игроков: %s", roundNumber + 1, connectionNames(players)));
        } else {
            System.out.println(String.format("Якубович: приглашаю победителей групповых этапов: %s", connectionNames(players)));
        }
    }

    public String connectionNames(Player[] players) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < players.length; i++) {
            stringBuilder.append(players[i].getPlayerName());
            if (i < players.length - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    public void sayRoundQuestion(String question) {
        System.out.println("Якубович: Внимание вопрос!\n" + question);
    }

    public void victoryShout(String name, String city, boolean isFinalRound) {
        if (!isFinalRound) {
            System.out.println(String.format("Якубович: Молодец! %s из %s проходит в финал!", name, city));
        } else {
            System.out.println(String.format("Якубович: И перед нами победитель Капитал шоу поле чудес! " +
                    "Это %s из %s", name , city));
        }
    }

    public boolean checkTheAnswer(PlayerAnswer playerAnswer, String answer,Tableau tableau) {
        //boolean isCorrectAnswer = false;
        if (playerAnswer.getAnswerType() == 'б') {
            if (answer.toUpperCase().contains(String.valueOf(playerAnswer.getAnswerContentChar()))) {
                System.out.println("Якубович: Есть такая буква, откройте ее!");
                tableau.openLetter(playerAnswer.getAnswerContentChar());
                tableau.showTableau();
                //isCorrectAnswer = true;
                return true;
            } else {
                System.out.println("Якубович: Нет такой буквы! Следующий игрок, крутите барабан!\n"
                    + "__________________________________");
                return false;
            }
        } else {
            if (answer.equalsIgnoreCase(playerAnswer.getAnswerContent())) {
                System.out.println(String.format("Якубович: %s! Абсолютно верно!", answer));
                tableau.openWholeWord();
                tableau.showTableau();
                return true;
                //isCorrectAnswer = true;
            } else {
                System.out.println("Якубович: Неверно! Следующий игрок!\n"
                    + "__________________________________");
                return false;
            }
        }
    }
}
