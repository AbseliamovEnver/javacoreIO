package com.abseliamov.javacoreio.view;

import com.abseliamov.javacoreio.controller.SkillController;
import com.abseliamov.javacoreio.model.Skill;
import com.abseliamov.javacoreio.utils.PrintMenu;
import com.abseliamov.javacoreio.utils.ReadInputData;

import java.util.*;
import java.util.stream.Collectors;

public class SkillView {
    public long skillMenu() {
        Map<Integer, String> skillMenu = new TreeMap<>();

        skillMenu.put(1, "Create skill");
        skillMenu.put(2, "Get skill by ID");
        skillMenu.put(3, "Get skill by name");
        skillMenu.put(4, "Get all skills");
        skillMenu.put(5, "Delete skill by ID");
        skillMenu.put(0, "Main menu");

        Set<Map.Entry<Integer, String>> skillMenuSet = skillMenu.entrySet();

        PrintMenu.printMenu(skillMenuSet, "\tS K I L L S  ", 0, 5);
        long select = ReadInputData.readInputData(0, 5);

        return select;
    }

    public void skillView() {
        SkillController skillController = new SkillController();
        boolean marker = true;
        boolean menu = true;
        long select;

        while (marker) {
            select = menu ? skillMenu() : 1;
            switch ((int) select) {
                case 0:
                    marker = false;
                    break;
                case 1:
                    System.out.println("If you want to create a new skill enter \'Y\' or other key to continue:");
                    if (ReadInputData.readInputString().equalsIgnoreCase("Y")) {
                        System.out.println("Enter skill name:");
                        skillController.add(ReadInputData.readInputString());
                        menu = false;
                    } else
                        menu = true;
                    break;
                case 2:
                    Set<Skill> skillItemId;
                    if ((skillItemId = skillController.getListSkills()) != null) {
                        List<Skill> skillSorted = skillItemId.stream().collect(Collectors.toList());
                        Collections.sort(skillSorted);
                        for (Skill skill : skillSorted)
                            System.out.println(skill.getId() + "\t" + skill.getSkillName());
                        System.out.println("Enter skill ID or enter \'0\' to continue: ");
                        long id;
                        while ((id = ReadInputData.readInputData(0, Long.MAX_VALUE)) != 0) {
                            Skill skill = skillController.getById(id);
                            if (skill != null) {
                                System.out.println("Skill with id: \'" + id + "\' have value \'"
                                        + skill.getSkillName() + "\'.");
                                System.out.println("Select other skill id or enter \'0\' to continue:");
                            } else {
                                System.out.println("Skill with id \'" + id + "\' not found.");
                                System.out.println("Select correct skill id or enter \'0\' to continue:");
                            }
                        }
                    } else {
                        System.out.println("Please create new skill.");
                        menu = false;
                    }
                    break;
                case 3:
                    Set<Skill> skillItem;
                    if ((skillItem = skillController.getListSkills()) != null) {
                        List<Skill> skillSorted = skillItem.stream().collect(Collectors.toList());
                        Collections.sort(skillSorted);
                        for (Skill skill : skillSorted)
                            System.out.println(skill.getId() + "\t" + skill.getSkillName());
                        System.out.println("Enter skill name or enter \'0\' to continue: ");
                        String name;
                        while (!(name = ReadInputData.readInputString()).equals("0")) {
                            Skill skill = skillController.getByName(name);
                            if (skill != null) {
                                System.out.println("Skill with name: \'" + name + "\' have id \'" + skill.getId() + "\'.");
                                System.out.println("Select other skill name or enter \'0\' to continue:");
                            } else {
                                System.out.println("Skill with name \'" + name + "\' not found.");
                                System.out.println("Select correct skill name or enter \'0\' to continue:");
                            }
                        }
                    } else {
                        System.out.println("Please create new skill.");
                        menu = false;
                    }
                    break;
                case 4:
                    Set<Skill> skillSet;
                    System.out.println("List skills: \nID\tSKILLS");
                    if ((skillSet = skillController.getListSkills()) == null) {
                        System.out.println("Please create new skill.");
                        menu = false;
                    }else {
                        List<Skill> skillSorted = skillSet.stream().collect(Collectors.toList());
                        Collections.sort(skillSorted);
                        for (Skill skill : skillSorted)
                            System.out.println(skill.getId() + "\t" + skill.getSkillName());
                    }
                    break;
                case 5:
                    if (skillController.getListSkills() != null) {
                        System.out.println("Enter skill ID to delete or \'0\' to continue: ");
                        long id;
                        while ((id = ReadInputData.readInputData(0, Long.MAX_VALUE)) != 0) {
                            skillController.delete(id);
                            System.out.println("Select other skill id or enter \'0\' to continue:");
                        }
                    } else {
                        System.out.println("Please create new skill.");
                        menu = false;
                    }
                    break;
                default:
                    continue;
            }
        }
    }
}

