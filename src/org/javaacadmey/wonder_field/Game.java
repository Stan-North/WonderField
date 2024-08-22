package org.javaacadmey.wonder_field;

import org.javaacadmey.wonder_field.player.Player;
import org.javaacadmey.wonder_field.player.PlayerAnswer;
import org.javaacadmey.wonder_field.player.SecretBox;

import java.util.Scanner;

public class Game {

    private static final int AMOUNT_OF_PLAYERS = 3;
    private static final int AMOUNT_OF_ROUNDS = 4;
    private static final int AMOUNT_OF_GROUP_ROUNDS = 3;
    private static final int FINAL_ROUND_INDEX = 3;
    private static String SUPERQUESTION = null;
    private static String SUPERQUESTION_ANSWER = null;
    private static final int SUPER_GAME_WIN = 1; //статус - победа в супер игре
    private static final int SUPER_GAME_LOSE = 2; //статус - проигрыш в супер игре
    private static final int SUPER_GAME_REJECT = 3; //статус - отказ от супер игры
    private static String SUPER_GAME_GIFT = null;


    public static Scanner scanner = new Scanner(System.in);

    //Массивы с вопросами и ответами на каждый раунд;
    static String[] questions = new String[AMOUNT_OF_ROUNDS];
    static String[] answers = new String[AMOUNT_OF_ROUNDS];

    static String[] testQuestions = new String[] {
            "Какая страна не имеет столицы?",
            "Какое существо может задержать дыхание на 6 суток?",
            "Какое животное имеет 3 глаза?",
            "Какое живое существо может спать, не просыпаясь 3 года?"};

    static String[] testAnswers = new String[] {
            "Науру",
            "Скорпион",
            "Туатара",
            "Улитка"};

    Player[] winners = new Player[AMOUNT_OF_PLAYERS];
    static int roundCounter = 0;

    public void testInit() {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. " +
                "Вам нужно ввести вопросы и ответы для игры.");
        System.out.println("Иницализация закончена, игра начнется через 5 секунд");
        Drum drum = Drum.getInstance();
        drum.initDrum();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }

    public void init() {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. " +
                "Вам нужно ввести вопросы и ответы для игры.");
        Drum drum = Drum.getInstance();
        drum.initDrum();

        //записываем в массивы вопросы и ответы
        for (int stepCounter = 0; stepCounter < AMOUNT_OF_ROUNDS; stepCounter++) {
            String askQuestion = String.format("Введите вопрос #%d", stepCounter + 1);
            System.out.println(askQuestion);
            questions[stepCounter] = scanner.nextLine();

            String askAnswer = String.format("Введите ответ вопрос #%d", stepCounter + 1);
            System.out.println(askAnswer);
            answers[stepCounter] = scanner.nextLine();
        }
        System.out.println("Введите вопрос для супер игры");
        SUPERQUESTION = scanner.nextLine();
        System.out.println("Введите ответ для супер игры");
        SUPERQUESTION_ANSWER = scanner.nextLine();
        System.out.println("Иницализация закончена, игра начнется через 5 секунд");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }

    public static Player[] createPlayers() {
        int counter = 0;
        Player[] players = new Player[AMOUNT_OF_PLAYERS];
        while (counter != AMOUNT_OF_PLAYERS) {
            boolean isPlayerCreated = false;
            System.out.println(String.format("Игрок №%d представьтесь: имя,город.", counter + 1));
            String playerInfo = scanner.nextLine();
            while (!isPlayerCreated) {
                if (playerInfo != null && !playerInfo.isEmpty()) {
                    String[] splittedInfo = playerInfo.split(",");
                    Player player = new Player(splittedInfo[0], splittedInfo[1]);
                    players[counter] = player;
                    counter++;
                    isPlayerCreated = true;
                } else {
                    System.out.println("Введите : имя,город");
                    playerInfo = scanner.nextLine();
                }
            }
        }
        return players;
    }


    public boolean isTableuContainsUnknown(Tableau tableau) {
        return tableau.isContainUnknowletters();
    }


    public boolean playerMove(String question, Player player) {
        Tableau tableau = Tableau.getInstance();
        Yakubovich yakubovich = Yakubovich.getInstance();
        int countSuccesAnswers = 0;

        while (true) {

            if (countSuccesAnswers == 3) {
                SecretBox box1 = new SecretBox();
                SecretBox box2 = new SecretBox();
                SecretBox.selectBoxWithMoney(box1, box2);
                player.chooseTheBox(box1, box2);
            }

            PlayerAnswer playerAnswer = player.makeMove();
            if (playerAnswer == null) {
                return false;
            }
            boolean isCorrectAnswer = yakubovich.checkTheAnswer(playerAnswer, answers[roundCounter], tableau);
            boolean isTableContainsUnknown = isTableuContainsUnknown(tableau);

            if (isCorrectAnswer && !isTableContainsUnknown) {
                return true;
            } else if (isCorrectAnswer && isTableContainsUnknown) {
                countSuccesAnswers++;
                continue;
            } else {
                return false;
            }
        }
    }

    public void playRound(String question, Player[] players) {
        boolean isFinalRound = roundCounter == FINAL_ROUND_INDEX;
        Yakubovich yakubovich = Yakubovich.getInstance();
        while (true) {
            for (Player player : players) {
                boolean resultRound = playerMove(question, player);
                if (resultRound && isFinalRound) {
                    //попадает в супер игру
                    int superGameResult = playSuperGame(player, yakubovich);
                    if (superGameResult == SUPER_GAME_WIN) {
                        yakubovich.victoryShout(player.getPlayerName(), player.getPlayerCity(), isFinalRound);
                        System.out.println("Вещи, которые выиграл игрок:\n" + player.getGifts() +
                                "\nВыиграно денег: " + player.getPlayerMoney());
                        System.out.println("Итоговые очки победителя: " + player.getScore());
                        return;
                    } else if (superGameResult == SUPER_GAME_LOSE) {
                        yakubovich.endGameWithoutWinner();
                        return;
                    } else if (superGameResult == SUPER_GAME_REJECT) {
                        yakubovich.victoryShout(player.getPlayerName(), player.getPlayerCity(), isFinalRound);
                        System.out.println("Вещи, которые выиграл игрок:\n" + player.getGifts() +
                                "\nВыиграно денег: " + player.getPlayerMoney());
                        System.out.println("Итоговые очки победителя: " + player.getScore());
                        System.out.println("Секретной вещью был(о): " + SUPER_GAME_GIFT);
                        return;
                    }

                } else if (resultRound && !isFinalRound) {
                    yakubovich.victoryShout(player.getPlayerName(), player.getPlayerCity(),isFinalRound);
                    winners[roundCounter] = player;
                    return;
                } else if (!resultRound) {
                    continue;
                }
            }
        }
    }

    public void playAllGroupRounds() {
        Player[] players;
        Tableau tableau = Tableau.getInstance();
        Yakubovich yakubovich = Yakubovich.getInstance();
        while (roundCounter != AMOUNT_OF_GROUP_ROUNDS) {
            players = createPlayers();
            tableau.initTableau(answers[roundCounter]);
            yakubovich.invitePlayers(players, roundCounter);
            yakubovich.sayRoundQuestion(questions[roundCounter]);
            tableau.showTableau();
            playRound(questions[roundCounter], players);
            roundCounter++;
        }
    }

    public void playFinalRound() {
        Tableau tableau = Tableau.getInstance();
        Yakubovich yakubovich = Yakubovich.getInstance();
        tableau.initTableau(answers[FINAL_ROUND_INDEX]);
        yakubovich.invitePlayers(winners, roundCounter);
        yakubovich.sayRoundQuestion(questions[FINAL_ROUND_INDEX]);
        tableau.showTableau();
        playRound(questions[FINAL_ROUND_INDEX], winners);
    }

    private int playSuperGame(Player player, Yakubovich yakubovich) {
        int superGameResult = 0;
        player.chooseGifts();
        SUPER_GAME_GIFT = chooseTheSuperGift();
        System.out.println(player.getPlayerName() + ", Вы соглашаетесь сыграть в супер игру ? \n" +
                "Введите \"Да\" или \"Нет\" ");
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("Да")) {
            Tableau tableau = Tableau.getInstance();
            tableau.initTableau(SUPERQUESTION_ANSWER);
            yakubovich.sayRoundQuestion(SUPERQUESTION);
            tableau.showTableau();
            int answerCounter = 0;
            while (answerCounter != 3) {
                tableau.openLetter(player.shoutLetter());
                tableau.showTableau();
                answerCounter++;
            }
            System.out.println("Назовите слово");
            if (player.shoutWord().equalsIgnoreCase(SUPERQUESTION_ANSWER)) {
                player.setGifts(player.getGifts().append(SUPER_GAME_GIFT));
                superGameResult = SUPER_GAME_WIN;
            } else {
                superGameResult = SUPER_GAME_LOSE;
            }
        } else if (answer.equalsIgnoreCase("Нет")) {
            superGameResult = SUPER_GAME_REJECT;
        }
        return superGameResult;
    }

    public String chooseTheSuperGift() {
        int number = (int) ((Math.random() * 4) + 1);
        return SuperGiftsEnum.values()[number].getName();
    }

    public void start() {
        Yakubovich yakubovich = Yakubovich.getInstance();
        yakubovich.startGame();
        playAllGroupRounds();
        playFinalRound();
        yakubovich.endGame();
    }
}


