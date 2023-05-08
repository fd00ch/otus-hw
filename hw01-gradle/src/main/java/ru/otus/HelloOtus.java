package ru.otus;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class HelloOtus {
    public static void main(String[] args) {
        String stringForHashing = "HelloOtus";
        HashFunction hf = Hashing.sha256();
        HashCode hc = hf.newHasher()
                .putString(stringForHashing, Charsets.UTF_8)
                .hash();
        System.out.printf("Sha256 hash for %s is %s%n", stringForHashing, hc);
    }
}
