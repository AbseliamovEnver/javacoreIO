package com.abseliamov.javacoreio;

import com.abseliamov.javacoreio.view.MainMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();

        menu.start();

/*        String string = "4,pokj,wesxdf,[123,21, 2],1,ACTIVE";
        String regex = "\\[.*\\]";
        String regex1 = "\\,]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(string);

        String group = null;
        while (matcher.find()) {
            group = matcher.group();
            System.out.println(group);
        }

        String[] strings = group.substring(1, group.length() - 1).split(",");

        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i].trim());
        }

        System.out.println("**********************************");

        String[] arrayString = new String[10];
        String[] newArrayString = new String[10];

//        arrayString = string.split(",");
        arrayString = string.split(",");
        for (String str : arrayString) {
            System.out.println(str);
        }

        System.out.println("=========================");
        int skillId = arrayString.length - 5;
        System.out.println(skillId + "\n=======================");

        long id = Long.parseLong(arrayString[0]);
        String firstName = arrayString[1];
        String lastName = arrayString[2];
        long idSkill = Long.parseLong(arrayString[3].substring(1));
        long idSkill2 = Long.parseLong(arrayString[3].substring(1));
        long idAccount = Long.parseLong(arrayString[arrayString.length - 2]);
        String status = arrayString[arrayString.length - 1];

        System.out.println(id);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(idSkill);
        System.out.println(idAccount);
        System.out.println(status);

 */
    }
}
