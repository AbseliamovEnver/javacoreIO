package com.abseliamov.javacoreio.repository;

import com.abseliamov.javacoreio.model.Developer;
import com.abseliamov.javacoreio.model.Skill;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {

    Developer update(Developer developer, Long id);

    Developer getByName(String name);

    boolean addSkillDeveloper(Developer developer, Skill skill);
}
