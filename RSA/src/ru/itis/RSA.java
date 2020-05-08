package ru.itis;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private final static BigInteger one = new BigInteger("1");
    private final static SecureRandom random = new SecureRandom();

    private BigInteger privateKey; //d
    private BigInteger publicKey; //e
    private BigInteger modulus; //n

    // generate an N-bit (roughly) public and private key
    RSA(int N) {
        BigInteger p = BigInteger.probablePrime(N, random);
        BigInteger q = BigInteger.probablePrime(N, random);
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

        modulus = p.multiply(q);
        publicKey = new BigInteger("65537");     // рекомендуется брать
        privateKey = publicKey.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message)
    {
        return (message).modPow(publicKey, modulus);
    }

    public BigInteger decrypt(BigInteger message)
    {
        return (message).modPow(privateKey, modulus);
    }
    public String toString() {
        String s = "";
        s += "public  = " + publicKey + "\n";
        s += "private = " + privateKey + "\n";
        s += "modulus = " + modulus;
        return s;
    }
}
