package com.abseliamov.javacoreio.utils;

import java.io.*;

public class ReadInputData {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readInputString() {
        String data = null;
        try {
            data = reader.readLine();
        } catch (IOException e) {
            System.out.println("I/O Exception in readInputString method: " + e);
        }
        return data;
    }

    public static long readInputData(long min, long max) {
        boolean marker = true;
        long select = 0;
        while (marker) {
            try {
                select = Long.parseLong(reader.readLine());
                if (select >= min && select <= max)
                    marker = false;
                else
                    System.out.println("Please enter correct value: ");
            } catch (Exception ex) {
                System.out.println("Invalid value: " + select);
                System.out.println("Please enter correct integer value: ");
            }
        }
        return select;
    }
}
