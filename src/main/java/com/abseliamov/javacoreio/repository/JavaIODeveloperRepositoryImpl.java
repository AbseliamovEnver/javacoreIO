package com.abseliamov.javacoreio.repository;

import com.abseliamov.javacoreio.model.Developer;
import com.abseliamov.javacoreio.model.Skill;
import com.abseliamov.javacoreio.utils.CheckFile;
import com.abseliamov.javacoreio.utils.GetDeveloperList;
import com.abseliamov.javacoreio.utils.GetID;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    public static final String DEVELOPER_FILE = "src/main/resources/developers.txt";

    @Override
    public void add(Developer developer) {
        File file = CheckFile.checkFileExists(DEVELOPER_FILE);
        boolean addDeveloper = true;
        Set<Long> skillsId = new TreeSet<>();

        Set<Skill> skills = developer.getSkills();
        for (Skill skill : skills) {
            skillsId.add(skill.getId());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DEVELOPER_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(DEVELOPER_FILE, true))) {
            if (file.length() != 0) {
                String data;
                while ((data = reader.readLine()) != null) {
                    String[] developerData = data.split(",");
                    if (developerData[1].equalsIgnoreCase(developer.getFirstName())
                            && developerData[2].equalsIgnoreCase(developer.getLastName()))
                        addDeveloper = false;
                }
            }
            if (addDeveloper) {
                developer.setId(GetID.getID(DEVELOPER_FILE));
                writer.write(developer.getId() + "," + developer.getFirstName() + ","
                        + developer.getLastName() + "," + skillsId + ","
                        + developer.getAccount().getId() + "," + developer.getAccount().getStatus() + "\n");
                writer.flush();
            } else System.out.println("This developer already exists");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception read/write file in method add developer: " + e);
        }
    }

    @Override
    public Developer getById(Long id) {
        File file = CheckFile.checkFileExists(DEVELOPER_FILE);
        Set<Developer> developers;

        if (file.length() != 0) {
            developers = GetDeveloperList.getDevelopers(file);
            for (Developer developer : developers) {
                if (developer.getId() == id) {
                    return developer;
                }
            }
        } else System.out.println("File with developers is empty");
        return null;
    }


    @Override
    public Developer getByName(String name) {
        File file = CheckFile.checkFileExists(DEVELOPER_FILE);
        Set<Developer> developers;

        if (file.length() != 0) {
            developers = GetDeveloperList.getDevelopers(file);
            for (Developer developer : developers) {
                if (developer.getFirstName().equals(name))
                    return developer;
            }
        } else System.out.println("File with developers is empty");
        return null;
    }

    @Override
    public Set<Developer> getAll() {
        File file = CheckFile.checkFileExists(DEVELOPER_FILE);
        Set<Developer> developers = null;

        if (file.length() != 0) {
            developers = GetDeveloperList.getDevelopers(file);
        } else System.out.println("File with developers is empty");
        return developers;
    }

    @Override
    public Developer update(Developer developer, Long id) {
        File file = CheckFile.checkFileExists(DEVELOPER_FILE);
        Developer updateDeveloper = null;
        Set<Long> skillsId = new TreeSet<>();

        Set<Skill> skills = developer.getSkills();
        for (Skill skill : skills) {
            skillsId.add(skill.getId());
        }

        if (file.length() != 0) {
            Set<Developer> developers = GetDeveloperList.getDevelopers(file);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEVELOPER_FILE, false))) {
                for (Developer developerItem : developers) {
                    if (developerItem.getId() == id) {
                        writer.write(developer.getId() + "," + developer.getFirstName() + ","
                                + developer.getLastName() + "," + skillsId + ","
                                + developer.getAccount().getId() + "," + developer.getAccount().getStatus() + "\n");
                        updateDeveloper = developerItem;
                        continue;
                    }
                    writer.write(developerItem.getId() + "," + developerItem.getFirstName() + ","
                            + developerItem.getLastName() + "," + skillsId + ","
                            + developerItem.getAccount().getId() + "," + developer.getAccount().getStatus() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Exception writing file in method delete developer: " + e);
            }
        }
        return updateDeveloper;
    }

    @Override
    public void delete(Long id) {
        File file = CheckFile.checkFileExists(DEVELOPER_FILE);
        boolean developerDelete = false;
        Set<Long> skillsId = new TreeSet<>();
        Developer developerId = null;

        Set<Skill> skills = developerId.getSkills();
        for (Skill skill : skills) {
            skillsId.add(skill.getId());
        }

        if (file.length() != 0) {
            Set<Developer> developerList = GetDeveloperList.getDevelopers(file);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEVELOPER_FILE, false))) {
                for (Developer developer : developerList) {
                    if (developer.getId() == id) {
                        developerDelete = true;
                        System.out.println("Developer with id \'" + id + "\' and name \'"
                                + developer.getFirstName() + "\' deleted successfully.");
                        continue;
                    }
                    writer.write(developer.getId() + "," + developer.getFirstName() + ","
                            + developer.getLastName() + "," + skillsId + ","
                            + developer.getAccount().getId() + "," + developer.getAccount().getStatus() + "\n");
                }
                if (developerDelete)
                    System.out.println("Developer with id \'" + id + "\' not found.");
            } catch (IOException e) {
                System.out.println("Exception writing file in method delete developer: " + e);
            }
        }
    }

    @Override
    public boolean addSkillDeveloper(Developer developer, Skill newSkill) {
        File file = CheckFile.checkFileExists(DEVELOPER_FILE);
        boolean addSkill = false;
        Set<Long> skillsId = new TreeSet<>();
        Developer developerId = null;

        Set<Skill> skills = developerId.getSkills();
        for (Skill skill : skills) {
            skillsId.add(skill.getId());
        }
        if (file.length() != 0) {
            Set<Developer> developerList = GetDeveloperList.getDevelopers(file);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEVELOPER_FILE, false))) {
                for (Developer developerItem : developerList) {
                    if (developerItem.getId() == developer.getId()) {
                        writer.write(developer.getId() + "," + developer.getFirstName() + ","
                                + developer.getLastName() + "," + skillsId + ","
                                + developer.getAccount().getId() + "," + developer.getAccount().getStatus() + "\n");
                        addSkill = true;
                        continue;
                    }
                    writer.write(developerItem.getId() + "," + developerItem.getFirstName() + ","
                            + developerItem.getLastName() + "," + skillsId + ","
                            + developerItem.getAccount().getId() + "," + developer.getAccount().getStatus() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Exception writing file in method delete developer: " + e);
            }
        }
        return addSkill;
    }
}
