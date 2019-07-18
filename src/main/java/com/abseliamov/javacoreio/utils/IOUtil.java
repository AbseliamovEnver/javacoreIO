package com.abseliamov.javacoreio.utils;

import com.abseliamov.javacoreio.controller.SkillController;
import com.abseliamov.javacoreio.model.Account;
import com.abseliamov.javacoreio.model.AccountStatus;
import com.abseliamov.javacoreio.model.Developer;
import com.abseliamov.javacoreio.model.Skill;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static Set<Developer> getDevelopers(File file) {
        Set<Developer> developers = new HashSet<>();
        SkillController skillController = new SkillController();
        Set<Skill> skillSet;
        skillSet = skillController.getListSkills();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String data;
            while ((data = reader.readLine()) != null) {
                String[] item = data.split(",");
                long idDeveloper = Long.parseLong(item[0]);
                String firstName = item[1];
                String lastName = item[2];
                long idAccount = Long.parseLong(item[item.length - 2]);
                AccountStatus status = AccountStatus.valueOf(item[item.length - 1]);

                String regex = "\\[.*\\]";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(data);

                String group = null;
                if (matcher.find()) {
                    group = matcher.group();
                }
                String[] skillId = group.substring(1, group.length() - 1).split(",");

                Set<Skill> skills = new HashSet<>();
                for (int i = 0; i < skillId.length; i++) {
                    long id = Long.parseLong(skillId[i].trim());
                    for (Skill skillItem : skillSet) {
                        if (skillItem.getId() == id)
                            skills.add(new Skill(id, skillItem.getSkillName()));
                    }
                }
                developers.add(new Developer(idDeveloper, firstName, lastName, skills, new Account(idAccount, status)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception reading file in method get developer by id: " + e);
        }
        return developers;
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
