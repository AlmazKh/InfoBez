package ru.itis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Encryptor enc = new Encryptor();
        try {
            enc.encrypt("input.txt");
            enc.decrypt("encrypt.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void encript() {

    }

}
