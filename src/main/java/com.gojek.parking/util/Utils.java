package com.gojek.parking.util;

public class Utils {

    public static Boolean checkIfStringContainsCharacters(String input) {

        long actualCount = input.toLowerCase()
                .chars()
                .filter(ch -> ch >= 'a' && ch <= 'z')
                .count();

        return (int) actualCount == input.length();
    }
}
