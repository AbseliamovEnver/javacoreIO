package com.abseliamov.module14.utils;

import com.abseliamov.module14.model.Account;
import com.abseliamov.module14.model.AccountStatus;
import com.abseliamov.module14.model.Developer;
import com.abseliamov.module14.model.Skill;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetDeveloperList {
    public static Set<Developer> getDevelopers(File file) {
        Set<Developer> developers = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String data;
            while ((data = reader.readLine()) != null) {
                String[] item = data.split("\t");
                long id = Long.parseLong(item[0]);
                String name = item[1];
                String surname = item[2];
                String skillsData = item[3];
                String accountData = item[4] + "\t" + item[5] + "\t" + item[6];
                Set<Skill> skills = new HashSet<>();

                Pattern patternID = Pattern.compile("\\d+");
                Pattern patternName = Pattern.compile("'(.*)'");
                Pattern patternStatus = Pattern.compile("=\\s.*");

                if (skillsData.length() != 0) {
                    String[] skillArray = skillsData.split(",");
                    long idSkill = 0;
                    String nameSkill = null;
                    for (int i = 0; i < skillArray.length; i++) {
                        if (i % 2 == 0) {
                            Matcher skillId = patternID.matcher(skillArray[i]);
                            while (skillId.find()) {
                                idSkill = Long.parseLong(skillId.group());
                            }
                        } else {
                            Matcher skillName = patternName.matcher(skillArray[i]);
                            while (skillName.find()) {
                                nameSkill = skillName.group().substring(1, skillName.group().length() - 1);
                            }
                            skills.add(new Skill(idSkill, nameSkill));
                        }
                    }
                }
                long accId = 0;
                String accLogin = null;
                String accStatus = null;
                Account account = null;

                if (accountData.length() != 0) {
                    Matcher accountId = patternID.matcher(item[4]);
                    Matcher accountLogin = patternName.matcher(item[5]);
                    Matcher accountStatus = patternStatus.matcher(item[6]);

                    while (accountId.find()) {
                        accId = Long.parseLong(accountId.group());
                    }
                    while (accountLogin.find()) {
                        accLogin = accountLogin.group().substring(1, accountLogin.group().length() - 1);
                    }
                    while (accountStatus.find()) {
                        accStatus = accountStatus.group().substring(2);
                    }

                    account = new Account(accId, accLogin, null, AccountStatus.valueOf(accStatus));
                }
                developers.add(new Developer(id, name, surname, skills, account));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception reading file in method get developer by id: " + e);
        }
        return developers;
    }
}
