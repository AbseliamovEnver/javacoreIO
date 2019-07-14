package com.abseliamov.javacoreio.model;

public class Account implements Comparable<Account> {
    private long id;
    private AccountStatus status;

    public Account(long id, AccountStatus status) {
        this.id = id;
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

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "id = " + id + "\tstatus = " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != account.id) return false;
        return status == account.status;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Account o) {
        return (int) (this.id - o.getId());
    }
}
