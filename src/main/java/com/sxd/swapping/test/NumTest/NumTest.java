package com.sxd.swapping.test.NumTest;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class NumTest {

    public static void main(String[] args) {
        Set<Integer>  set = new HashSet<>();

        int a = 1000000;
        for (int i = 0; i < a; i++) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase().replaceAll("[^a-zA-Z_]","");
            set.add(uuid.hashCode());
        }

        System.out.println(set.size());
    }
}
