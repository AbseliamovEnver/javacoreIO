package com.abseliamov.javacoreio.utils;

import java.util.Map;
import java.util.Set;

public class PrintMenu {
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
}
