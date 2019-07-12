package com.abseliamov.module14.controller;

import com.abseliamov.module14.model.Account;
import com.abseliamov.module14.model.Developer;
import com.abseliamov.module14.model.Skill;
import com.abseliamov.module14.repository.DeveloperRepository;
import com.abseliamov.module14.repository.JavaIODeveloperRepositoryImpl;

import java.util.Set;

public class DeveloperController {
    DeveloperRepository developerRepository = new JavaIODeveloperRepositoryImpl();

    public void add(long id, String name, String surname, Set<Skill> skills, Account account) {
        Developer developer = new Developer(id, name, surname, skills, account);
        developerRepository.add(developer);
    }

    public Developer getById(long id) {
        Developer developer = developerRepository.getById(id);
        if (developer != null)
            return developer;
        return developer;
    }

    public Developer getByName(String name) {
        Developer developer = developerRepository.getByName(name);
        if (developer != null)
            return developer;
        return developer;
    }

    public Set<Developer> getListDeveloper() {
        Set<Developer> developers = developerRepository.getAll();
        if (developers != null) {
            for (Developer developer : developers)
                System.out.println(developer);
            return developers;
        } else System.out.println("List developers is empty.");
        return null;
    }

    public void update(long id, String name, String surname) {
        Developer developer = developerRepository.getById(id);
        Developer updateDeveloper = developerRepository.update(new Developer(id,
                name, surname, developer.getSkills(), developer.getAccount()), id);
        if (updateDeveloper != null)
            System.out.println("Developer with id \'" + updateDeveloper.getId() + "\' and name \'"
                    + updateDeveloper.getName() + "\' update successfully.");
        else
            System.out.println("Developer with id \'" + id + "\' not found.");
    }

    public Developer delete(long id) {
        Developer developer = developerRepository.delete(id);
        if (developer != null)
            return developer;
        return developer;
    }

    public boolean checkDeveloper(long id) {
        Developer developer = developerRepository.getById(id);
        if (developer != null)
            return true;
        return false;
    }

    public boolean checkDeveloperSkill(Set<Skill> skills, long idSkill, long idDeveloper) {
        Developer developer = developerRepository.getById(idDeveloper);
        Set<Skill> developerSkills = developer.getSkills();
        boolean skillExist = false;
        boolean idSkillExist = false;
        Skill newSkill = null;

        for (Skill skill : skills) {
            if (skill.getId() == idSkill) {
                idSkillExist = true;
                newSkill = new Skill(skill.getId(), skill.getSkillName());
                for (Skill skillList : developerSkills) {
                    if (skillList.getId() == idSkill) {
                        skillExist = true;
                        break;
                    }
                }
            }
        }
        if (!idSkillExist) {
            System.out.println("Skill with id \'" + idSkill + "\' not found");
            return false;
        } else if (skillExist) {
            System.out.println("This skill already exist.");
            return false;
        }
        return developerRepository.addSkillDeveloper(developer, newSkill);
    }
}
