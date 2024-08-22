package org.javaacadmey.wonder_field.player;

import org.javaacadmey.wonder_field.Drum;
import org.javaacadmey.wonder_field.Game;
import org.javaacadmey.wonder_field.GiftsEnum;
import org.javaacadmey.wonder_field.SecretBox;

public class Player {
    private final String playerName;
    private final String playerCity;
    private int score = 0;
    private int playerMoney = 0;
    private StringBuilder gifts = new StringBuilder();

    public StringBuilder getGifts() {
        return gifts;
    }

    public void setGifts(StringBuilder gifts) {
        this.gifts = gifts;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerCity() {
        return playerCity;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(int playerMoney) {
        this.playerMoney = playerMoney;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player(String playerName, String playerCity) {
        this.playerName = playerName;
        this.playerCity = playerCity;
        this.playerMoney = 0;
    }

    public char shoutLetter() {
        boolean isAllowedLetter = false;
        String playerAnswer;
        char spokenLetter = ' ';

        String errorMessage = "Ошибка! это не русская буква, введите русскую букву";

        while (!isAllowedLetter) {
            System.out.println("Введите русскую букву");
            playerAnswer =  Game.scanner.next().toUpperCase();
            if (playerAnswer.length() > 1 || playerAnswer.equals("\n") || playerAnswer.isEmpty()) {
                System.out.print(errorMessage);
            } else {
                char incomingLetter = playerAnswer.charAt(0);
                if ((incomingLetter >= 'А' && incomingLetter <= 'Я')
                        || incomingLetter =='Ё') {
                    spokenLetter = incomingLetter;

                    isAllowedLetter = true;

                    System.out.println("Игрок " + getPlayerName() + ":" + " буква " + spokenLetter);
                    return spokenLetter;
                }
            }
        }
        return spokenLetter;
    }

    public String shoutWord() {
        boolean isWordSpoken = false;
        String playerWord = null;
        System.out.println("Введите русское слово");
        while (!isWordSpoken) {
            playerWord = Game.scanner.next().toUpperCase();
            if (playerWord != null) {
                System.out.println("Игрок " + getPlayerName() + ":" + " слово " + playerWord);
                isWordSpoken = true;
            } else {
                System.out.println("Введите русское слово");
            }
        }
        return playerWord;
    }

    public PlayerAnswer makeMove() {
        System.out.println("Ход игрока " + getPlayerName() + ", " + getPlayerCity());
        boolean isMoveProceed = Drum.getInstance().spinTheDrum(this);
        if (!isMoveProceed) {
            return null;
        }
        System.out.println("Если хотите букву нажмите 'б' и enter, если хотите слово нажмите 'c' и enter");

        while (true) {
            String answer = Game.scanner.next();
            char answerType =  answer.charAt(0);
            if (answerType == 'б') {
                char answerLetter = shoutLetter();
                return new PlayerAnswer(answerType, answerLetter);
            } else if (answerType == 'с') {
                String answerWord = shoutWord();
                return new PlayerAnswer(answerType, answerWord);
            } else {
                System.out.print("Некорректное значение, введите 'б' или 'с'");
            }
        }
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void multiplyScore() {
        this.score *= 2;
    }

    public void chooseTheBox(SecretBox box1, SecretBox box2) {
        while (true) {
            System.out.println("Игрок " + this.getPlayerName() + " отгадал три буквы подряд!" +
                    " \nВынесите в зал шкатулки! \nВыберите шкатулку! \nВведите 1 или 2..");
            int chosenBox = Game.scanner.nextInt();

            if (chosenBox == 1) {
                System.out.println("Внутри шкатулки лежит: " + box1.getMoneyInsideBox() + " рублей");
                if (box1.getMoneyInsideBox() != 0) {
                    System.out.println("Поздравляем! Деньги буду добавлены к вашему итоговому счету");
                    this.setPlayerMoney(box1.getMoneyInsideBox());
                } else {
                    System.out.println("К сожалению, вы не угадали! Шкатулка пуста");
                }

                break;
            } else if (chosenBox == 2) {
                System.out.println("Внутри шкатулки лежит:" + box2.getMoneyInsideBox() + " рублей");
                if (box2.getMoneyInsideBox() != 0) {
                    System.out.println("Поздравляем! Деньги буду добавлены к вашему итоговому счету");
                    this.setPlayerMoney(box1.getMoneyInsideBox());
                } else {
                    System.out.println("К сожалению, вы не угадали! Шкатулка пуста");
                }

                break;
            } else {
                System.out.println("Выберите шкатулку! + \n + Введите 1 или 2..");
            }
        }
    }

    public void chooseGifts() {
        GiftsEnum giftsEnum;
        System.out.println("Поздравляем, вы проходите в супе игру");
        System.out.println("Ваше количество очков: " + this.getScore());
        System.out.println("Вы можете выбрать следующие призы из списка:");
        for (GiftsEnum giftsEnum1 : GiftsEnum.values()) {
            System.out.println((giftsEnum1.ordinal() + 1) + ". " + giftsEnum1.getName() + ": " + giftsEnum1.getPrice() + " очков");
        }

        while (true) {
            int counter = 0;
            System.out.print("Введите номер предмета, который хотите забрать, " +
                    "если больше взять не можете - введите \"cтоп\" \n");
            String selectedGift = Game.scanner.next();
            if (selectedGift.equalsIgnoreCase("стоп")) {
                break;
            } else {
                int numberOfGift = Integer.parseInt(selectedGift);
                numberOfGift--;
                if ((numberOfGift < 0) || (numberOfGift > GiftsEnum.values().length) || selectedGift == null) {
                    System.out.println("Введите число из списка");
                    continue;
                }
                if (GiftsEnum.values()[numberOfGift].getPrice() > this.getScore()) {
                    System.out.println("У вас недостаточно очков, выберите что-то другое");
                } else {
                    String gift = GiftsEnum.values()[numberOfGift].getName();
                    this.gifts.append(gift + "\n");
                    this.setScore(this.getScore() - GiftsEnum.values()[numberOfGift].getPrice());
                    System.out.println("Ваше количество очков: " + this.getScore());
                    continue;
                }
            }
        }
    }
}
