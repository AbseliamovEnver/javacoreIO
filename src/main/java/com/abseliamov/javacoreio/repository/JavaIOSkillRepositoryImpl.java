package com.abseliamov.javacoreio.repository;

import com.abseliamov.javacoreio.model.Skill;
import com.abseliamov.javacoreio.utils.IOUtil;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class JavaIOSkillRepositoryImpl implements SkillRepository {
    public static final String SKILLS_FILE = "src/main/resources/skills.txt";

    @Override
    public void add(Skill skill) {
        File file = IOUtil.checkFileExists(SKILLS_FILE);
        boolean addSkill = true;
        String data;

        try (BufferedReader reader = new BufferedReader(new FileReader(SKILLS_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(SKILLS_FILE, true))) {
            if (file.length() != 0) {
                while ((data = reader.readLine()) != null) {
                    String[] skillData = data.split(",");
                    if (skillData[1].equalsIgnoreCase(skill.getSkillName()))
                        addSkill = false;
                }
            }
            if (addSkill) {
                skill.setId(IOUtil.getID(SKILLS_FILE));
                writer.write(skill.getId() + "," + skill.getSkillName() + "\n");
                System.out.println("Skill added is successful");
                writer.flush();
            } else System.out.println("This skill already exists");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception read/write file in method add skill: " + e);
        }
    }

    @Override
    public Skill getById(Long id) {
        File file = IOUtil.checkFileExists(SKILLS_FILE);
        String data;

        try (BufferedReader reader = new BufferedReader(new FileReader(SKILLS_FILE))) {
            if (file.length() != 0) {
                while ((data = reader.readLine()) != null) {
                    String[] skillData = data.split(",");
                    if (Long.parseLong(skillData[0]) == id)
                        return new Skill(id, skillData[1]);
                }
            } else System.out.println("File with skills is empty");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception reading file in method get skill by id: " + e);
        }
        return null;
    }

    @Override
    public Skill getByName(String name) {
        File file = IOUtil.checkFileExists(SKILLS_FILE);
        String data;

        try (BufferedReader reader = new BufferedReader(new FileReader(SKILLS_FILE))) {
            if (file.length() != 0) {
                while ((data = reader.readLine()) != null) {
                    String[] skillData = data.split(",");
                    if (skillData[1].equalsIgnoreCase(name))
                        return new Skill(Long.parseLong(skillData[0]), skillData[1]);
                }
            } else System.out.println("File with skills is empty");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception reading file in method get skill by name: " + e);
        }
        return null;
    }

    @Override
    public Set<Skill> getAll() {
        File file = IOUtil.checkFileExists(SKILLS_FILE);
        Set<Skill> skills = new HashSet<>();
        String data;

        try (BufferedReader reader = new BufferedReader(new FileReader(SKILLS_FILE))) {
            if (file.length() != 0) {
                while ((data = reader.readLine()) != null) {
                    String[] skillData = data.split(",");
                    skills.add(new Skill(Long.parseLong(skillData[0]), skillData[1]));
                }
                return skills;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception reading file in method get all skills: " + e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        File file = IOUtil.checkFileExists(SKILLS_FILE);
        Set<Skill> skills = new HashSet<>();
        boolean skillDelete = false;
        String data;

        try (BufferedReader reader = new BufferedReader(new FileReader(SKILLS_FILE))) {
            if (file.length() != 0) {
                while ((data = reader.readLine()) != null) {
                    String[] skillData = data.split(",");
                    if (Long.parseLong(skillData[0]) == id) {
                        skillDelete = true;
                        System.out.println("Skill with id: \'" + id + "\' and name \'"
                                + skillData[1] + "\' deleted successfully.");
                        continue;
                    }
                    skills.add(new Skill(Long.parseLong(skillData[0]), skillData[1]));
                }
                if (!skillDelete)
                    System.out.println("Skill with id \'" + id + "\' not found.");
            }

        } catch (IOException e) {
            System.out.println("Exception reading from file in method delete skill: " + e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SKILLS_FILE, false))) {
            for (Skill skill : skills)
                writer.write(skill.getId() + "," + skill.getSkillName() + "\n");
        } catch (IOException e) {
            System.out.println("Exception writing file in method delete skill: " + e);
        }
    }

    @Override
    public Set<Skill> getData() {
        File file = IOUtil.checkFileExists(SKILLS_FILE);
        Set<Skill> setSkills = new HashSet<>();
        String data;

        try (BufferedReader reader = new BufferedReader(new FileReader(SKILLS_FILE))) {
            if (file.length() != 0) {
                while ((data = reader.readLine()) != null) {
                    String[] skill = data.split(",");
                    for (int i = 0; i < skill.length; i++)
                        setSkills.add(new Skill(Long.parseLong(skill[0]), skill[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Exception read skills file: " + e);
        }
        return setSkills;
    }
}
