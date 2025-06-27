package com.example.kawiarenka;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static OrderManager instance;
    private final List<DrinkModel> drinks = new ArrayList<>();
    private final List<Snack> snacks = new ArrayList<>();

    private OrderManager() {}

    public static OrderManager getInstance() {
        if (instance == null) instance = new OrderManager();
        return instance;
    }

    public void addDrink(DrinkModel drink) {
        drinks.add(drink);
    }

    public void addSnack(Snack snack) {
        snacks.add(snack);
    }

    public List<DrinkModel> getDrinks() {
        return new ArrayList<>(drinks);
    }

    public List<Snack> getSnacks() {
        return new ArrayList<>(snacks);
    }

    public double getTotalPrice() {
        double total = 0;
        for (DrinkModel d : drinks) total += d.getPrice();
        for (Snack s : snacks) total += s.getPrice();
        return total;
    }

    public void clear() {
        drinks.clear();
        snacks.clear();
    }
}