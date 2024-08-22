package org.javaacadmey.wonder_field;

import org.javaacadmey.wonder_field.player.Player;

import java.util.Random;

public class Drum {
    private final int[] drumCells = new int[14];

    private static Drum INSTANCE;

    private Drum() {
    }

    public static Drum getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Drum();
        }
        return INSTANCE;
    }

    public void initDrum() {
        int score = 100;
        for (int i = 0; i < 12; i++) {
            drumCells[i] = score;
            score += 100;
        }
        drumCells[12] = 0;
        drumCells[13] = 0;
    }

    public boolean spinTheDrum(Player player) {
        boolean isMoveProceed = false;
        System.out.println("*** Игрок " + player.getPlayerName() + " вращает барабан ***");

        int number = (int) (Math.random() * 14);
        if (number == 13) {
            System.out.println("*** Барабан остановился на секторе проуска хода ***");
            System.out.println("Игрок " + player.getPlayerName() + " пропускает ход!");
        } else if (number == 12) {
            System.out.println("*** Барабан остановился на секторе умножения очков ***");
            System.out.println("У игрока " + player.getPlayerName() + " очки умножаются на 2!");
            player.multiplyScore();
            isMoveProceed = true;
        } else {
            System.out.println("*** Барабан остановился на секторе " + drumCells[number] + " ***");
            System.out.println("Игрок " + player.getPlayerName() + " получает " + drumCells[number] + " очков!");
            player.addScore(drumCells[number]);
            isMoveProceed = true;
        }
        return isMoveProceed;
    }
}
