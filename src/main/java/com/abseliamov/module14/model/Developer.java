package com.abseliamov.module14.model;

import java.util.Set;

public class Developer {
    public static final String DEVELOPER_FILE = "src/main/java/com/abseliamov/module14/database/developers.txt";
    private long id;
    private String name;
    private String surName;
    private Set<Skill> skills;
    private Account account;

    public Developer(long id, String name, String surName, Set<Skill> skills, Account account) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.skills = skills;
        this.account = account;
    }

    public Developer(Developer developer) {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Developer developer = (Developer) o;

        if (id != developer.id) return false;
        if (name != null ? !name.equals(developer.name) : developer.name != null) return false;
        if (surName != null ? !surName.equals(developer.surName) : developer.surName != null) return false;
        if (skills != null ? !skills.equals(developer.skills) : developer.skills != null) return false;
        return account != null ? account.equals(developer.account) : developer.account == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (skills != null ? skills.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Developer:\n" + "\tid = " + id + ",\n\tname = \'" + name + "\'" + ",\n\tsurName = \'" + surName + "\'" +
                ",\n\tskills:\n\t\t" + skills + "\'\n\taccount:\n\t\t" + account;
    }
}
