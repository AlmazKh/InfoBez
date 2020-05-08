package ru.itis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Scanner;

public class MainDESStore {
    private static final File keyFile = new File("src/main/resources/lavina/public.txt");
    private static final File textFile = new File("src/main/resources/lavina/private.txt");

    public static void main(String[] args) throws Exception {
        String text = "1B5456AB4713D53D";
        String key = "A7BF09BE4736CE3A";

        String changeText = "1B5456AB4723D53D";
        String changedKey = "A7CF09BE4736CE3A";

        DESStore desAnalyserBefore = new DESStore();
        desAnalyserBefore.encrypt(text, key);
        System.out.println("---------------------------------");
        System.out.println("After 1 bit changing in key");
        System.out.println("----------------------------------");
        DESStore desAnalyserAfter = new DESStore();
        desAnalyserAfter.encrypt(changeText, changedKey);
        System.out.println("----------------------------------");
        try (
                BufferedWriter keyWriter = new BufferedWriter(new FileWriter(keyFile));
                BufferedWriter textWriter = new BufferedWriter(new FileWriter(textFile))
        ) {
            Map<Integer, String> keyMapAfter = desAnalyserAfter.getKeyRoundMap();
            Map<Integer, String> keyMapBefore = desAnalyserBefore.getKeyRoundMap();
            for (Map.Entry<Integer, String> round : keyMapBefore.entrySet()) {
                String before = round.getValue();
                String after = keyMapAfter.get(round.getKey());
                int count = 0;
                for (int i = 0; i < before.length(); i++) {
                    if (before.charAt(i) != after.charAt(i)) {
                        count++;
                    }
                }
                keyWriter.write("Round " + round.getKey() + " :");
                keyWriter.write(String.valueOf(count));
                keyWriter.write('\n');
                keyWriter.write(before + "\n");
                keyWriter.write(after + "\n");
            }
            Map<Integer, String> textMapAfter = desAnalyserAfter.getTextRoundMap();
            Map<Integer, String> textMapBefore = desAnalyserBefore.getTextRoundMap();

            for (Map.Entry<Integer, String> round : textMapBefore.entrySet()) {
                String before = round.getValue();
                String after = textMapAfter.get(round.getKey());
                int count = 0;
                for (int i = 0; i < before.length(); i++) {
                    if (before.charAt(i) != after.charAt(i)) {
                        count++;
                    }
                }
                textWriter.write("Round " + round.getKey() + " :");
                textWriter.write(String.valueOf(count));
                textWriter.write('\n');
                textWriter.write(before + "\n");
                textWriter.write(after + "\n");
            }
        }
    }

    public static String binaryToString(String binary) {
        String s = "";
        for (int index = 0; index < binary.length(); index += 8) {
            String temp = binary.substring(index, index + 8);
            int num = Integer.parseInt(temp, 2);
            char letter = (char) num;
            s = s + letter;
        }
        return s;
    }
}
