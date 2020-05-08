package ru.itis;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final File inputFile = new File("src/main/resources/input.txt");

    private static final File encryptKeyFile = new File("src/main/resources/encrypt/key.txt");
    private static final File encryptTextFile = new File("src/main/resources/encrypt/text.txt");

    private static final File rsaPublicKey = new File("src/main/resources/keyPair/public.txt");
    private static final File rsaPrivateKey = new File("src/main/resources/keyPair/private.txt");

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int keySize = scanner.nextInt();
        RSA rsa = new RSA(keySize);
        writeRSAKeys(rsaPublicKey, rsaPrivateKey, rsa);
        DES cipher = new DES();
        String desKey = generateRandomPassword(16);
        try (
                BufferedReader reader = new BufferedReader((new FileReader(inputFile)));
                BufferedWriter textWriter = new BufferedWriter((new FileWriter(encryptTextFile)));
                BufferedWriter keyWriter = new BufferedWriter((new FileWriter(encryptKeyFile)))
        ) {
            while (reader.ready()) {
                String input = reader.readLine();
                Result encrypt = cipher.encrypt(input, desKey);
                String decrypt = cipher.decrypt(encrypt.getText(), desKey);
                textWriter.write(encrypt.getText());
                System.out.println("---------------------");
                System.out.println("Decrypted: " + decrypt);
            }
            BigInteger encryptedDesKey = rsa.encrypt(new BigInteger(desKey.getBytes()));
            String decryptedDesKey = new String(rsa.decrypt(encryptedDesKey).toByteArray());
            keyWriter.write(Arrays.toString(encryptedDesKey.toByteArray()));
            System.out.println("Des key - " + desKey);
            System.out.println("Decrypted Des key - " + decryptedDesKey);
        }
    }

    private static void writeRSAKeys(File publicKeyFile, File privateKeyFile, RSA rsa) throws Exception {
        try (
                BufferedWriter writerPublic = new BufferedWriter(new FileWriter(publicKeyFile));
                BufferedWriter writerPrivate = new BufferedWriter(new FileWriter(privateKeyFile))
        ) {
            writerPublic.write("e - " + rsa.getPublicKey());
            writerPublic.write('\n');
            writerPublic.write("n - " + rsa.getModulus());
            writerPrivate.write("d - " + rsa.getPrivateKey());
            writerPrivate.write('\n');
            writerPrivate.write("n - " + rsa.getModulus());
        }
    }

    private static String generateRandomPassword(int length) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, length);
    }
}
