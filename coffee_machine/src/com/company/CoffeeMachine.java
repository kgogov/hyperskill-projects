package com.company;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {

        final CoffeeMaker coffeeMaker = new CoffeeMaker(400,540,120,9,550);

        final Scanner scanner = new Scanner(System.in);

        while (true) {
            String action = scanner.next();

            if ("exit".equals(action)) {
                break;
            } else {
                coffeeMaker.interact(action);
            }
        }
    }
}
