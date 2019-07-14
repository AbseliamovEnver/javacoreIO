package com.abseliamov.javacoreio.repository;

import com.abseliamov.javacoreio.model.Skill;

import java.util.Set;

public interface SkillRepository extends GenericRepository<Skill, Long>{

    Set<Skill> getData();
}
