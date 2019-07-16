package com.abseliamov.javacoreio.model;

public class Skill implements Comparable<Skill> {
    private long id;
    private String skillName;

    public Skill(long id, String skillName) {
        this.id = id;
        this.skillName = skillName;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skillName='" + skillName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        if (id != skill.id) return false;
        return skillName != null ? skillName.equals(skill.skillName) : skill.skillName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (skillName != null ? skillName.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Skill o) {
        return (int) (this.id - o.getId());
    }
}
