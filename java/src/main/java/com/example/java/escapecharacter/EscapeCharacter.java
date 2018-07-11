package com.example.java.escapecharacter;

/**
 * Created by snowson on 18-1-16.
 */

public class EscapeCharacter {
    public static void main(String[] args) {
        String content = "a||b||c||d||e";
//        String content = "a\\b\\c\\d";
        String[] arrays = content.split("\\|\\|");
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(i + " : " +arrays[i] + ", ");
        }
//        System.out.println("\u007c");
//        String content = "a××b××c";
//        String[] arrays = content.split("××");
//        for(int i = 0; i < arrays.length; i++) {
//            System.out.println(arrays[i]);
//        }
//        System.out.println(Integer.toBinaryString(~0));
    }
}
