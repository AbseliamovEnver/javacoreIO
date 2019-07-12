package com.abseliamov.module14.repository;

import com.abseliamov.module14.model.Developer;
import com.abseliamov.module14.model.Skill;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {

    Developer update(Developer developer, Long id);

    Developer getByName(String name);

    boolean addSkillDeveloper(Developer developer, Skill skill);
}
