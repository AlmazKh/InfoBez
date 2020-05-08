package ru.itis;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();
        int N = scanner.nextInt();
        RSA key = new RSA(N);
        System.out.println(key);

        // create random message, encrypt and decrypt
//        BigInteger message = new BigInteger(N - 1, random);

        //// create message by converting string to integer
         String s = "AABB09082736CCDD";
         byte[] bytes = s.getBytes();
         BigInteger message = new BigInteger(bytes);

        BigInteger encrypt = key.encrypt(message);
        BigInteger decrypt = key.decrypt(encrypt);
        System.out.println("message   = " + message);
        System.out.println("encrypted = " + encrypt);
        System.out.println("decrypted = " + decrypt);

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("DES_key.txt"));
            writer.write(String.valueOf(encrypt));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
