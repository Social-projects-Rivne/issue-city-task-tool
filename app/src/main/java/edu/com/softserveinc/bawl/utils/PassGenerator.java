package edu.com.softserveinc.bawl.utils;


public class PassGenerator {

    public static String generate(int from, int to){
        StringBuilder sb = new StringBuilder();
        int n = 8; // how many characters in password
        String set = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890"; // characters to choose from
        for (int i= 0; i < n; i++) {
            int k = (int)(Math.random()*set.length());   // random number between 0 and set.length()-1 inklusive
            sb.append(set.charAt(k));
        }
        String result = sb.toString();

        return result;
    }
}
