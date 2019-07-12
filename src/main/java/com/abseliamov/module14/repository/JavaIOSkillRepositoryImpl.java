package com.abseliamov.module14.repository;

import com.abseliamov.module14.model.Skill;
import com.abseliamov.module14.utils.GetID;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class JavaIOSkillRepositoryImpl implements SkillRepository {

    @Override
    public void add(Skill skill) {
        boolean addSkill = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(Skill.SKILLS_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(Skill.SKILLS_FILE, true))) {
            File file = new File(Skill.SKILLS_FILE);
            if (file.exists() && file.length() != 0) {
                String data;
                while ((data = reader.readLine()) != null) {
                    String[] skillData = data.split("\t");
                    if (skillData[1].equalsIgnoreCase(skill.getSkillName()))
                        addSkill = false;
                }
            }
            if (addSkill) {
                skill.setId(GetID.getID(Skill.SKILLS_FILE));
                writer.write(skill.getId() + "\t" + skill.getSkillName() + "\n");
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
        try (BufferedReader reader = new BufferedReader(new FileReader(Skill.SKILLS_FILE))) {
            File file = new File(Skill.SKILLS_FILE);
            if (file.exists() && file.length() != 0) {
                String data;
                while ((data = reader.readLine()) != null) {
                    String[] skillData = data.split("\t");
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
        try (BufferedReader reader = new BufferedReader(new FileReader(Skill.SKILLS_FILE))) {
            File file = new File(Skill.SKILLS_FILE);
            if (file.exists() && file.length() != 0) {
                String data;
                while ((data = reader.readLine()) != null) {
                    String[] skillData = data.split("\t");
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
        Set<Skill> skills = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Skill.SKILLS_FILE))) {
            File file = new File(Skill.SKILLS_FILE);
            if (file.exists() && file.length() != 0) {
                String data;
                while ((data = reader.readLine()) != null) {
                    String[] skillData = data.split("\t");
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
    public Skill delete(Long id) {
        Set<Skill> skills = new HashSet<>();
        Skill skillDelete = null;
        String data;

        try (BufferedReader reader = new BufferedReader(new FileReader(Skill.SKILLS_FILE))) {
            File file = new File(Skill.SKILLS_FILE);
            if (file.exists() && file.length() != 0) {
                while ((data = reader.readLine()) != null) {
                    String[] skillData = data.split("\t");
                    if (Long.parseLong(skillData[0]) == id) {
                        skillDelete = new Skill(id, skillData[1]);
                        continue;
                    }
                    skills.add(new Skill(Long.parseLong(skillData[0]), skillData[1]));
                }
            }

        } catch (IOException e) {
            System.out.println("Exception reading from file in method delete skill: " + e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Skill.SKILLS_FILE, false))) {
            for (Skill skill : skills)
                writer.write(skill.getId() + "\t" + skill.getSkillName() + "\n");
        } catch (IOException e) {
            System.out.println("Exception writing file in method delete skill: " + e);
        }
        return skillDelete;
    }

    @Override
    public Set<Skill> getData() {
        Set<Skill> setSkills = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(Skill.SKILLS_FILE))) {
            File file = new File(Skill.SKILLS_FILE);
            if (file.exists() && file.length() != 0) {
                String data;
                while ((data = reader.readLine()) != null) {
                    String[] skill = data.split("\t");
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
