package com.abseliamov.module14.controller;

import com.abseliamov.module14.model.Skill;
import com.abseliamov.module14.repository.JavaIOSkillRepositoryImpl;
import com.abseliamov.module14.repository.SkillRepository;

import java.util.Set;

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
        Set<Skill> skills = skillRepository.getAll();
        if (skills != null) {
            for (Skill skill : skills)
                System.out.println(skill.getId() + "\t" + skill.getSkillName());
            return skills;
        } else System.out.println("List skills is empty.");
        return null;
    }

    public Skill delete(Long id) {
        Skill skill = skillRepository.delete(id);
        skill = skill != null ? skill : null;
        return skill;
    }
}
