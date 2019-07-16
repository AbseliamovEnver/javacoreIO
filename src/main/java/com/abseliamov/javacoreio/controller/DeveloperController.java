package com.abseliamov.javacoreio.controller;

import com.abseliamov.javacoreio.model.Account;
import com.abseliamov.javacoreio.model.Developer;
import com.abseliamov.javacoreio.model.Skill;
import com.abseliamov.javacoreio.repository.DeveloperRepository;
import com.abseliamov.javacoreio.repository.JavaIODeveloperRepositoryImpl;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DeveloperController {
    DeveloperRepository developerRepository = new JavaIODeveloperRepositoryImpl();

    public void add(long id, String firstName, String lastName, Set<Skill> skills, Account account) {
        Developer developer = new Developer(id, firstName, lastName, skills, account);
        developerRepository.add(developer);
    }

    public Developer getById(long id) {
        Developer developer = developerRepository.getById(id);
        if (developer != null)
            return developer;
        return developer;
    }

    public Developer getByName(String firstName) {
        Developer developer = developerRepository.getByName(firstName);
        if (developer != null)
            return developer;
        return developer;
    }

    public Set<Developer> getListDeveloper() {
        Set<Developer> developers = developerRepository.getAll();
        if (developers != null) {
            List<Developer> developerSorted = developers.stream().collect(Collectors.toList());
            Collections.sort(developerSorted);
            System.out.println("LIST OF DEVELOPERS:");
            System.out.println("ID\tFIRST NAME\t\tLAST NAME\t\tSKILL ID\t\tSKILL NAME\t\t\t\tACCOUNT ID\t\t\t\tACCOUNT STATUS");
            for (Developer developer : developerSorted)
                System.out.println(developer.getId() + "\t" + developer.getFirstName() + "\t\t\t"
                        + developer.getLastName() + "\t\t\t" + developer.getSkills() + "\t\t\t"
                        + developer.getAccount());
            return developers;
        } else System.out.println("List developers is empty.");
        return null;
    }

    public void update(long id, String firstName, String lastName, Set<Skill> skills, Account account) {
        Developer developer = new Developer(id, firstName, lastName, skills, account);
        Developer updateDeveloper = developerRepository.update(developer, id);
        if (updateDeveloper != null)
            System.out.println("Developer with id \'" + updateDeveloper.getId() + "\' and firstName \'"
                    + updateDeveloper.getFirstName() + "\' update successfully.");
        else
            System.out.println("Developer with id \'" + id + "\' not found.");
    }

    public void delete(long id) {
        developerRepository.delete(id);
    }

    public boolean checkDeveloper(long id) {
        Developer developer = developerRepository.getById(id);
        if (developer != null)
            return true;
        return false;
    }

    public boolean checkDeveloperSkill(long idSkill, long idDeveloper) {
        Developer developer = developerRepository.getById(idDeveloper);
        SkillController skills = new SkillController();
        Set<Skill> allSkills = skills.getListSkills();
        Set<Skill> developerSkills = developer.getSkills();
        boolean skillExist = false;
        boolean idSkillExist = false;

        for (Skill skill : allSkills) {
            if (skill.getId() == idSkill) {
                idSkillExist = true;
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
        return true;
    }
}
