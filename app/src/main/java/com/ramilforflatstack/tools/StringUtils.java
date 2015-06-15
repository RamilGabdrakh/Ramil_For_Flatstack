package com.ramilforflatstack.tools;

/**
 * Created by Ramil on 15.06.2015.
 */
public class StringUtils {

    public static String toLength(String input, int length){
        if (input.length() >= length) {
            return input;
        } else {
            return toLength("0" + input, length);
        }
    }
}