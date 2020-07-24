package com.company;

public class CoffeeMaker {
    private CoffeeMakerState state;
    private int waterNow;
    private int milkNow;
    private int coffeeBeansNow;
    private int disposableCupsNow;
    private int moneyNow;

    public CoffeeMaker(int waterNow, int milkNow, int coffeeBeansNow, int disposableCupsNow, int moneyNow) {
        this.waterNow = waterNow;
        this.milkNow = milkNow;
        this.coffeeBeansNow = coffeeBeansNow;
        this.disposableCupsNow = disposableCupsNow;
        this.moneyNow = moneyNow;
        state = CoffeeMakerState.CHOOSE_ACTION;
        System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
    }

    public void interact(String action) {
        switch (state) {
            case CHOOSE_ACTION:
                chooseAction(action);
                break;
            case BUY:
                buy(action);
                break;
            case FILL_WATER:
                fillWater(action);
                break;
            case FILL_MILK:
                fillMilk(action);
                break;
            case FILL_COFFEE_BEANS:
                fillCoffeeBeans(action);
                break;
            case FILL_DISPOSABLE_CUPS:
                fillDisposableCups(action);
                break;
        }
    }

    private void chooseAction(String action) {
        switch(action) {
            case "buy":
                this.state = CoffeeMakerState.BUY;
                System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                break;
            case "fill":
                this.state = CoffeeMakerState.FILL_WATER;
                System.out.println("\nWrite how many ml of water do you want to add:");
                break;
            case "take":
                System.out.printf("\nI gave you $%d\n", moneyNow);
                take();
                System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
                break;
            case "remaining":
                System.out.printf("\nThe coffee machine has:\n%d of water\n%d of milk\n%d of coffee beans\n" +
                        "%d of disposable cups\n$%d of money\n", waterNow, milkNow, coffeeBeansNow, disposableCupsNow, moneyNow);
                System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
                break;
            default:
                System.out.println("Illegal action: " + action);
                System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
        }
    }

    private void take() {
        moneyNow = 0;
    }

    private void buy(String coffeeType) {
        if (disposableCupsNow < 1) {
            System.out.println("Sorry, not enough disposable cups");
        } else {
            switch (coffeeType) {
                case "1":
                    if (waterNow >= 250 && coffeeBeansNow >= 16) {
                        System.out.println("I have enough resources, making you a coffee!\n");
                        waterNow -= 250;
                        coffeeBeansNow -= 16;
                        disposableCupsNow -= 1;
                        moneyNow += 4;
                    } else if (waterNow < 250) {
                        System.out.println("Sorry, not enough water!");
                    } else {
                        System.out.println("Sorry, not enough coffee beans!");
                    }
                    break;
                case "2":
                    if (waterNow >= 350 && milkNow >= 75 && coffeeBeansNow >= 20) {
                        System.out.println("I have enough resources, making you a coffee!\n");
                        waterNow -= 350;
                        milkNow -= 75;
                        coffeeBeansNow -= 20;
                        disposableCupsNow -= 1;
                        moneyNow += 7;
                    } else if (waterNow < 350) {
                        System.out.println("Sorry, not enough water!");
                    } else if (milkNow < 75) {
                        System.out.println("Sorry, not enough milk!");
                    } else {
                        System.out.println("Sorry, not enough coffee beans!");
                    }
                    break;
                case "3":
                    if (waterNow >= 200 && milkNow >= 100 && coffeeBeansNow >= 12) {
                        System.out.println("I have enough resources, making you a coffee!\n");
                        waterNow -= 200;
                        milkNow -= 100;
                        coffeeBeansNow -= 12;
                        disposableCupsNow -= 1;
                        moneyNow += 6;
                    } else if (waterNow < 200) {
                        System.out.println("Sorry, not enough water!");
                    } else if (milkNow < 100) {
                        System.out.println("Sorry, not enough milk!");
                    } else {
                        System.out.println("Sorry, not enough coffee beans!");
                    }
                    break;
                case "back":
                    break;
            }
        }
        this.state = CoffeeMakerState.CHOOSE_ACTION;
        System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
    }

    private void fillWater(String addedWater) {
        waterNow += Integer.parseInt(addedWater);
        this.state = CoffeeMakerState.FILL_MILK;
        System.out.println("Write how many ml of milk do you want to add:");
    }

    private void fillMilk(String addedMilk) {
        milkNow += Integer.parseInt(addedMilk);
        this.state = CoffeeMakerState.FILL_COFFEE_BEANS;
        System.out.println("Write how many grams of coffee beans do you want to add:");
    }

    private void fillCoffeeBeans(String addedCoffeeBeans) {
        coffeeBeansNow += Integer.parseInt(addedCoffeeBeans);
        this.state = CoffeeMakerState.FILL_DISPOSABLE_CUPS;
        System.out.println("Write how many disposable cups of coffee do you want to add:");
    }

    private void fillDisposableCups(String addedDisposableCups) {
        disposableCupsNow += Integer.parseInt(addedDisposableCups);
        this.state = CoffeeMakerState.CHOOSE_ACTION;
        System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
    }
}
