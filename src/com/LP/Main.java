package com.LP;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        var off = CoffeeMaker.machineState.OFF;
        CoffeeMaker.mainMenu();
        while (!CoffeeMaker.getState().equals(off)) {
            CoffeeMaker.processInput(scanner.next());
        }
    }
}

