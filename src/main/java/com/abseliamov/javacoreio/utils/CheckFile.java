package com.abseliamov.javacoreio.utils;

import java.io.File;
import java.io.IOException;

public class CheckFile {
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
}
