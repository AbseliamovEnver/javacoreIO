package com.abseliamov.javacoreio.controller;

import com.abseliamov.javacoreio.model.Skill;
import com.abseliamov.javacoreio.repository.JavaIOSkillRepositoryImpl;
import com.abseliamov.javacoreio.repository.SkillRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SkillController {
    SkillRepository skillRepository = new JavaIOSkillRepositoryImpl();

    public void add(String skillName) {
        Skill skill = new Skill(0, skillName);
        skillRepository.add(skill);
    }

    public Skill getById(long id) {
        Skill skill = skillRepository.getById(id);
        skill = skill != null ? skill : null;
        return skill;
    }

    public Skill getByName(String name) {
        Skill skill = skillRepository.getByName(name);
        skill = skill != null ? skill : null;
        return skill;
    }

    public Set<Skill> getListSkills() {
        Set<Skill> skills;
        if ((skills = skillRepository.getAll()) != null) {
            List<Skill> skillSorted = skills.stream().collect(Collectors.toList());
            Collections.sort(skillSorted);
            for (Skill skill : skillSorted)
//                System.out.println(skill.getId() + "\t" + skill.getSkillName());
            return skills;
        } else System.out.println("List skills is empty.");
        return null;
    }

    public void delete(Long id) {
        skillRepository.delete(id);
    }
}
