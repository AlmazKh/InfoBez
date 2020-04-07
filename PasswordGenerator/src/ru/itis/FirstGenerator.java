package ru.itis;

import java.util.Random;

public class FirstGenerator {

    public static void main(String[] args) {
        // P = 10^(-5); V = 20 паролей/мин; T = 6 дней
        // P = 10^(-5); V = 28800 паролей/день; T = 6 дней
        // 1. Латинские прописные буквы (A-Z)
        // 2. Русские строчные буквы (а-я)
        // S = [V * T / P] = 172800 * 10^5
        // S <= A^L    Алфавит = 26 + 33 = 59 букв
        /*System.out.println("17280000000");
        System.out.println((long)Math.pow(59, 6)); // L = 6
        System.out.println("A = " + (int)'A' + "; " + "Z = " + (int)'Z');
        System.out.println("а = " + (int)'а' + "; " + "я = " + (int)'я');*/

        int L = 6;
        StringBuilder password = new StringBuilder();
        for(int i = 0; i < L; i++) {
            boolean part = new Random().nextBoolean();
            if(part) {
                password.append((char)getRandomNumber(65, 90)); // A - Z
            } else {
                password.append((char)getRandomNumber(1072, 1103)); // а - я
            }
        }
        System.out.println("Generated password: " + password);
    }

    private static int getRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return new Random().nextInt((max - min) + 1) + min;
    }
}
