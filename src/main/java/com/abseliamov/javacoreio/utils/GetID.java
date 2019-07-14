package com.abseliamov.javacoreio.utils;

import java.io.*;
import java.util.SortedSet;
import java.util.TreeSet;

public class GetID {
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
