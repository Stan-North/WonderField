package org.javaacadmey.wonder_field;

public class SecretBox {
    private static final int COUNT_OF_BOXES = 2;
    private static final int AMOUNT_OF_MONEY = 3_000_000;

    private int moneyInsideBox = 0;

    public int getMoneyInsideBox() {
        return moneyInsideBox;
    }

    public void setMoneyInsideBox(int moneyInsideBox) {
        this.moneyInsideBox = moneyInsideBox;
    }

    public static void selectBoxWithMoney(SecretBox box1, SecretBox box2) {
        int chosenBox = (int) ((Math.random() * COUNT_OF_BOXES) + 1);
        if (chosenBox == 1) {
            box1.setMoneyInsideBox(AMOUNT_OF_MONEY);
        } else {
            box2.setMoneyInsideBox(AMOUNT_OF_MONEY);
        }
    }

}

