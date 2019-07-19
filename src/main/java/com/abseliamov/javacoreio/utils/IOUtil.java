package com.abseliamov.javacoreio.utils;

import java.io.*;
import java.util.*;

public class IOUtil {
    public static void printMenu(Set<Map.Entry<Integer, String>> menu, String nameMenu, int minItem, int maxItem) {
        System.out.println("*********************************");
        System.out.println(nameMenu + " M E N U");
        System.out.println("*********************************");

        for (Map.Entry<Integer, String> list : menu) {
            System.out.print(list.getKey() + ": ");
            System.out.println(list.getValue());
        }

        System.out.println("*********************************");
        System.out.println("Select menu item from \"" + minItem + "\" to \"" + maxItem + "\": ");
        System.out.println("*********************************");
    }

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

    public static File checkFileExists(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
                return file;
            } catch (IOException e) {
                System.out.println("Exception create file \'" + fileName + "\' " + e);
            }
        }
        return file;
    }

    public static long getID(String path) {
        SortedSet<String> strings = new TreeSet<>();
        long lastId = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            File file = new File(path);
            String data;
            while ((data = reader.readLine()) != null)
                strings.add(data);

            if (file.exists() && file.length() != 0) {
                lastId = Long.parseLong(strings.last().split(",")[0]);
                return ++lastId;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastId;
    }
}
