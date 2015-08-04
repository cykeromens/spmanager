/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.web.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author root
 */
public class StringBuilderUtil {
    private final String ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
    private final String NUMBERS = "0123456789";
    private final String NON_CHARACTERS = ";:$#!*+=\\//?";
    private final StringBuilder stringBuilder = new StringBuilder();


    private StringBuilder generateAlphabets(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(ALPHABETS.charAt(Utils.random(ALPHABETS.length()-1)));
        }
        return sb;
    }

    private StringBuilder generateNumbers(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(NUMBERS.charAt(Utils.random(NUMBERS.length()-1)));
        }
        return sb;
    }

    private StringBuilder generateNonCharacters(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(NON_CHARACTERS.charAt(Utils.random(NON_CHARACTERS.length()-1)));
        }
        return sb;
    }

    public String generateRandomString(int size) {
        for (int i = 0; i < size; i++) {
            int turn = Utils.random(3);
            switch (turn) {
                case 1:
                    stringBuilder.append(generateAlphabets(1));
                    break;
                case 2:
                    stringBuilder.append(generateNumbers(1));
                    break;
                case 3:
                    stringBuilder.append(generateNonCharacters(1));
                    break;
                default:
                    stringBuilder.append(generateAlphabets(1));
            }
        }
        String newString = new String(stringBuilder);
        return newString;
    }

    public int generatePin(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(NUMBERS.charAt(Utils.random(NUMBERS.length()-1)));
        }
        return Integer.parseInt(sb.toString());
    }

    public String generateAccountNumber(){
        Calendar date = new GregorianCalendar();
        StringBuilder sb = new StringBuilder();

        String year = getTwoDigitYear(date.get(Calendar.YEAR));
        String month = getTwoDigits(date.get(Calendar.MONDAY));
        String day = getTwoDigits(date.get(Calendar.DAY_OF_MONTH));
        String hour = getTwoDigits(date.get(Calendar.HOUR));
        String minutes = getTwoDigits(date.get(Calendar.MINUTE));
        String seconds = getTwoDigits(date.get(Calendar.SECOND));

        sb.append(year);
        sb.append(month);
        sb.append(day);
        sb.append(hour);
        sb.append(minutes);
        sb.append(seconds);

        return new String(sb);
    }

    private static String getTwoDigits(int value){
        StringBuilder first = new StringBuilder().append(value);
        if(value < 9){
            first.insert(0, "0");
        }
        return new String(first);
    }

    private static String getTwoDigitYear(int value){
        int year = value%2000;
        return String.valueOf(year);
    }
}
