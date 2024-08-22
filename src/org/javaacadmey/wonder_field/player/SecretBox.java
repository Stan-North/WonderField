package org.javaacadmey.wonder_field.player;

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
//создаем шкатулки в классе гейм
//У шкатулки есть поле инт - по дефолту нулл
//класс шкатулки
//изначальное нул
// конструктор - создается две шкатулки, в одну из них добавляются деньги - методом
//

    //Этап: создание 2 шкатулок и супер игры
    //1. При отгадывании 3 букв подряд одним игроком, должны появится шкатулки. Одна с деньгами, другая без.
    // У игрока должен быть выбор одной из шкатулок. Если получит деньги, то должен сохранить у себя их.
    //        2. При инициализации добавляется супервопрос.
    //        2. Победитель попадает в супер игру. Где сначала на свои баллы выбирает вещи (список вещей и их стоимость придумываете сами).
    //Потом ему предлагается сыграть в супер игру. Ему случайно должна выпасть какой то супер подарок (создать список супер подарков).
    //Если соглашается играть в супер игру:
    //Называет 3 буквы из потенциального вопроса. Игрок должен отгадать вопрос.
    // Если отгадывает то перечисляются все вещи которые он выиграл (и даже деньги) + рандомная вещь.
    //
    //Если не соглашается играть в супер игру:
    //перечисляются все вещи которые он выиграл (и даже деньги)
    //Потом Якубович говорит что за секретная вещь была.
