package com.LP;

public class CoffeeMaker {
    private static int money = 550;
    private static int waterAmount = 400;
    private static int milkAmount = 540;
    private static int coffeeBeanAmount = 120;
    private static int cupAmount = 9;
    private static machineState state = machineState.CHOOSE_ACTION;
    private static int fillCounter = 0;
    private static final int[] supplyFill = new int[4];

    enum machineState {
        CHOOSE_ACTION, CHOOSE_COFFEE, FILL_STATE, OFF
    }

    public static machineState getState() {
        return state;
    }

    public static void processInput(String input) {
        if (state.equals(machineState.CHOOSE_ACTION)) {
            switch (input) {
                case "buy":
                    state = machineState.CHOOSE_COFFEE;
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    break;
                case "fill":
                    state = machineState.FILL_STATE;
                    System.out.println("Write how many ml of water you want to add:");
                    break;
                case "take":
                    take();
                    state = machineState.CHOOSE_ACTION;
                    mainMenu();
                    break;
                case "remaining":
                    printState();
                    state = machineState.CHOOSE_ACTION;
                    mainMenu();
                    break;
                case "exit":
                    state = machineState.OFF;
                    break;
                default:
                    mainMenu();
                    break;
            }
        } else if (state.equals(machineState.CHOOSE_COFFEE)) {
            buy(input);
            state = machineState.CHOOSE_ACTION;
            mainMenu();
        } else if (state.equals(machineState.FILL_STATE)) {
            supplyFiller(input);
        }
    }

    private static void espresso() {
        int cost = 4;
        int water = 250;
        int milk = 0;
        int coffeeBeans = 16;
        int cups = 1;

        if (!canMake(water, milk, coffeeBeans, cups)) {
            return;
        }
        waterAmount -= water;
        coffeeBeanAmount -= coffeeBeans;
        cupAmount -= cups;
        money += cost;
    }

    private static void latte() {
        int cost = 7;
        int water = 350;
        int milk = 75;
        int coffeeBeans = 20;
        int cups = 1;

        if (!canMake(water, milk, coffeeBeans, cups)) {
            return;
        }

        waterAmount -= water;
        milkAmount -= milk;
        coffeeBeanAmount -= coffeeBeans;
        cupAmount -= cups;
        money += cost;
    }

    private static void cappuccino() {
        int cost = 6;
        int water = 200;
        int milk = 100;
        int coffeeBeans = 12;
        int cups = 1;

        if (!canMake(water, milk, coffeeBeans, cups)) {
            return;
        }

        waterAmount -= water;
        milkAmount -= milk;
        coffeeBeanAmount -= coffeeBeans;
        cupAmount -= cups;
        money += cost;
    }

    private static boolean canMake(int water, int milk, int coffeeBeans, int cups) {
        boolean make = true;
        if (waterAmount - water < 0) {
            System.out.println("Sorry, not enough water");
            make = false;
        }
        if (milkAmount - milk < 0) {
            System.out.println("Sorry, not enough milk");
            make = false;
        }
        if (coffeeBeanAmount - coffeeBeans < 0) {
            System.out.println("Sorry, not enough coffee beans");
            make = false;
        }
        if (cupAmount - cups < 0) {
            System.out.println("Sorry, not enough cups");
            make = false;
        }
        if (make) {
            System.out.println("I have enough resources, making you a coffee!");
        }
        return make;
    }

    private static void printState() {
        System.out.println("The coffee machine has:");
        System.out.println(waterAmount + " ml of water");
        System.out.println(milkAmount + " ml of milk");
        System.out.println(coffeeBeanAmount + " g of coffee beans");
        System.out.println(cupAmount + " disposable cups");
        System.out.println("$" + money + " of money");
    }

    private static void buy(String coffeeDrink) {
        switch (coffeeDrink) {
            case "1":
                espresso();
                break;
            case "2":
                latte();
                break;
            case "3":
                cappuccino();
                break;
            case "back":
            default:
                break;
        }
    }

    private static void fill(int water, int milk, int coffeeBeans, int cups) {
        waterAmount += water;
        milkAmount += milk;
        coffeeBeanAmount += coffeeBeans;
        cupAmount += cups;
    }

    private static void supplyFiller(String input) {
        switch (fillCounter) {
            case 0:
                supplyFill[fillCounter] = Integer.parseInt(input);
                ++fillCounter;
                System.out.println("Write how many ml of milk you want to add:");
                break;
            case 1:
                supplyFill[fillCounter] = Integer.parseInt(input);
                ++fillCounter;
                System.out.println("Write how many grams of coffee beans you want to add:");
                break;
            case 2:
                supplyFill[fillCounter] = Integer.parseInt(input);
                ++fillCounter;
                System.out.println("Write how many disposable cups of coffee you want to add:");
                break;
            case 3:
                supplyFill[fillCounter] = Integer.parseInt(input);
                fill(supplyFill[0], supplyFill[1], supplyFill[2], supplyFill[3]);
                fillCounter = 0;
                state = machineState.CHOOSE_ACTION;
                mainMenu();
                break;
            default:
                break;
        }
    }

    private static void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    public static void mainMenu() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }
}
