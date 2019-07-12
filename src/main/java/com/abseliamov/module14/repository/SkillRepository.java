package com.abseliamov.module14.repository;

import com.abseliamov.module14.model.Skill;

import java.util.Set;

public interface SkillRepository extends GenericRepository<Skill, Long>{

    Set<Skill> getData();
}
