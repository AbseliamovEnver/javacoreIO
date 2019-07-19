package com.abseliamov.javacoreio.utils;

import com.abseliamov.javacoreio.controller.SkillController;
import com.abseliamov.javacoreio.model.Account;
import com.abseliamov.javacoreio.model.AccountStatus;
import com.abseliamov.javacoreio.model.Developer;
import com.abseliamov.javacoreio.model.Skill;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeveloperUtil {
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
}
