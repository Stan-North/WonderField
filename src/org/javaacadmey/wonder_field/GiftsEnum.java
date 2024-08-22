package org.javaacadmey.wonder_field;

public enum GiftsEnum {
    TV("Телевизор", 3000),
    FRIDGE("Холодильник", 2500),
    MICROVAE("Микроволновая печь", 1000),
    WASHER("Стиральная машина", 2000),
    LAWNMOVER("Газонокосилка", 900),
    COMPUTER("Компьютер", 4000);

    GiftsEnum(String name, int price) {
        this.name = name;
        this.price = price;
    }

    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
