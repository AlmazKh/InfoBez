package ru.itis;

import java.io.Console;
import java.util.Arrays;

public class SecondGenerator {
    public static void main(String[] args) {
        String starved = "starved";
        String carp = "carp";
        String shuck = "shuck";
        StringBuilder password = new StringBuilder();
        char let12;
        if(shuck.charAt(0) == 'z') {
            let12 = 'a';
        } else {
            let12 = (char)((int) shuck.charAt(0) + 1);
        }
        password.append(let12).append(let12);

        char let3;
        if(carp.charAt(2) == 'a') {
            let3 = 'z';
        } else {
            let3 = (char)((int) carp.charAt(2) - 1);
        }
        password.append(let3);

        char let4;
        if(shuck.length() % 2 == 1) {
            if(shuck.charAt(3) == 'z') {
                let4 = 'a';
            } else {
                let4 = (char)((int) shuck.charAt(3) + 1);
            }
        } else {
            if(shuck.charAt(shuck.length() / 2 - 1) == 'a') {
                let4 = 'z';
            } else {
                let4 = (char)((int) shuck.charAt(shuck.length() / 2 - 1) - 1);
            }
        }
        password.append(let4);

        char let5;
        if(starved.length() + carp.length() > 26) {
            let5 = (char)((starved.length() + carp.length()) % 26 + 96);
        } else {
            let5 = (char)((starved.length() + carp.length()) + 96);
        }
        password.append(let5);
        System.out.println(password);

        new SecondGenerator().passwordChecking(password.toString());
    }

    public void passwordChecking(String passwd) {
        Console console = System.console();
        if (console == null) {
            System.out.println("Couldn't get Console instance");
            System.exit(0);
        }

        char[] passwdArr = passwd.toCharArray();
        boolean correct = false;
        console.printf("Checking password%n");
        while (!correct) {
            char[] passwordArray = console.readPassword("Enter your password: ");
            if(Arrays.equals(passwordArray, passwdArr)) {
                console.printf("Password correct!");
                correct = true;
            } else {
                console.printf("Incorrect password. Please, try again%n");
            }
        }

    }

}
