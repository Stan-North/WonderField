package org.javaacadmey.wonder_field;

public enum SuperGiftsEnum {
    CAR("Автомобиль"),
    APARTMENT("Квартира"),
    MOTORCYCLE("Мотоцикл"),
    TRAVEL("Путешествие");

    private String name;

    SuperGiftsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
