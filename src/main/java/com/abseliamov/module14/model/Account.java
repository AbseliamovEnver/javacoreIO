package com.abseliamov.module14.model;

public class Account implements Comparable<Account> {
    public static final String ACCOUNTS_FILE = "src/main/java/com/abselyamov/practice/module14/database/accounts.txt";
    private long id;
    private String login;
    private String password;
    private AccountStatus status;

    public Account(long id, String login, String password, AccountStatus status) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public Account(Account account) {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "id = " + id + "\tlogin = '" + login + '\'' + "\tstatus = " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != account.id) return false;
        if (login != null ? !login.equals(account.login) : account.login != null) return false;
        if (password != null ? !password.equals(account.password) : account.password != null) return false;
        return status == account.status;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Account o) {
        return (int) (this.id - o.getId());
    }
}
