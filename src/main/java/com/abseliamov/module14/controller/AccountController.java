package com.abseliamov.module14.controller;

import com.abseliamov.module14.model.Account;
import com.abseliamov.module14.model.AccountStatus;
import com.abseliamov.module14.repository.AccountRepository;
import com.abseliamov.module14.repository.JavaIOAccountRepositoryImpl;

import java.util.Set;

public class AccountController {
    AccountRepository accountRepository = new JavaIOAccountRepositoryImpl();

    public void add(String login, String password, Long status) {
        AccountStatus accountStatus = null;
        for (int i = 0; i < AccountStatus.values().length; i++) {
            if (status == i)
                accountStatus = AccountStatus.values()[i];
        }
        Account account = new Account(0, login, password, accountStatus);
        accountRepository.add(account);
    }

    public Account getById(long id) {
        Account account = accountRepository.getById(id);
        account = account != null ? account : null;
        return account;
    }

    public Account getByName(String login) {
        Account account = accountRepository.getByName(login);
        account = account != null ? account : null;
        return account;
    }

    public Set<Account> getListAccounts() {
        Set<Account> accounts = accountRepository.getAll();
        if (accounts != null) {
            for (Account account : accounts)
                System.out.println("Account id:\t" + account.getId() + "\tlogin: "
                        + account.getLogin() + "\tstatus: " + account.getStatus());
            return accounts;
        } else System.out.println("List accounts is empty.");
        return null;
    }

    public void update(long id, String login, String password, long status) {
        AccountStatus accountStatus = null;
        for (int i = 0; i < AccountStatus.values().length; i++) {
            if (status == i)
                accountStatus = AccountStatus.values()[i];
        }
        boolean update = accountRepository.update(new Account(id, login, password, accountStatus));
        if (update)
            System.out.println("Update account is successful");
        else System.out.println("Account update failed.");

    }

    public Account delete(Long id) {
        Account account = accountRepository.delete(id);
        account = account != null ? account : null;
        return account;
    }

    public boolean checkAccount(Long id, String pass) {
        Account account = accountRepository.checkAccount(id, pass);
        if (account != null)
            return true;
        return false;
    }

    public void getListStatus() {
        for (int i = 0; i < AccountStatus.values().length; i++) {
            System.out.println(i + "\t" + AccountStatus.values()[i]);
        }
    }
}
