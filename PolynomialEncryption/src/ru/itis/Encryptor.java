package ru.itis;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Encryptor {

    int n = new Random().nextInt(5) + 3;
    List<Integer> primeNumbers = new ArrayList<>(createPrimeNumbers());
    List<Integer> listOfA = new ArrayList<>();
    int p = primeNumbers.get(new Random().nextInt(primeNumbers.size()));

    public void encrypt(String fileName) throws Exception {
        FileInputStream inputStream = new FileInputStream(fileName);
        Scanner scanner = new Scanner(inputStream);
        FileWriter file = new FileWriter("encrypt.txt");

        listOfA.add(1);
        for (int i = 0; i < n; i++) {
            listOfA.add(new Random().nextInt(4) + 1);
        }

        StringBuilder word = new StringBuilder();
        while (scanner.hasNext()) {
            word.append(scanner.next()).append("\n");
        }
        char[] word_char = word.toString().toCharArray();
        for (int i = 0; i < word_char.length; i++) {
            int char_numb = (int) word_char[i] - 1039;
            int y = 0;
            for (int j = n; j > 0; j--) {
                y += (int) Math.pow(char_numb, j) * listOfA.get(n - j);
            }
            y += listOfA.get(n);
            y = y % p;
            file.write(y + " ");
        }
        file.close();
    }

    public void decrypt(String fileName) throws Exception {
        FileInputStream inputStream = new FileInputStream(fileName);
        Scanner scanner = new Scanner(inputStream);
        FileWriter file = new FileWriter("decrypt.txt");
        while (scanner.hasNext()) {
            int let = scanner.nextInt();
            if (let < 0) {
                file.write("\n");
                continue;
            }

            for (int letter = 1; letter < 34; letter++) {
                int y = 0;
                for (int j = n; j > 0; j--) {
                    y += (int) Math.pow(letter, j) * listOfA.get(n - j);
                }
                y += listOfA.get(n);
                y = y % p;
                if (y == let) {
                    file.write((char) (letter + 1039));
                    break;
                }
            }
        }
        file.close();
    }

    public List<Integer> createPrimeNumbers() {
        List<Integer> primeNumbers = new ArrayList<>();

        for (int i = 500; i <= 1000; i++) {
            int counter = 0;
            for (int num = i; num >= 1; num--) {
                if (i % num == 0) {
                    counter = counter + 1;
                }
            }
            if (counter == 2) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
}
