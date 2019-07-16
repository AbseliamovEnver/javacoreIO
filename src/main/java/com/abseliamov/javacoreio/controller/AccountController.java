package com.abseliamov.javacoreio.controller;

import com.abseliamov.javacoreio.model.Account;
import com.abseliamov.javacoreio.model.AccountStatus;
import com.abseliamov.javacoreio.repository.AccountRepository;
import com.abseliamov.javacoreio.repository.JavaIOAccountRepositoryImpl;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountController {
    AccountRepository accountRepository = new JavaIOAccountRepositoryImpl();

    public void add(Long accountId) {
        AccountStatus accountStatus = null;
        for (int i = 0; i < AccountStatus.values().length; i++) {
            if (accountId == (i + 1))
                accountStatus = AccountStatus.values()[i];
        }
        Account account = new Account(accountId, accountStatus);
        accountRepository.add(account);
    }

    public Account getById(long id) {
        Account account = accountRepository.getById(id);
        account = account != null ? account : null;
        return account;
    }

    public Account getByName(String status) {
        Account account = accountRepository.getByName(status);
        account = account != null ? account : null;
        return account;
    }

    public Set<Account> getListAccounts() {
        Set<Account> accounts;
        if ((accounts = accountRepository.getAll()) != null) {
            List<Account> accountSorted = accounts.stream().collect(Collectors.toList());
            Collections.sort(accountSorted);
            System.out.println("ID\t" + "Status");
            for (Account account : accountSorted)
                System.out.println(account.getId() + "\t" + account.getStatus());
            return accounts;
        } else System.out.println("List accounts is empty.");
        return null;
    }

    public void delete(Long id) {
        accountRepository.delete(id);
    }

    public void getListStatus() {
        for (int i = 0; i < AccountStatus.values().length; i++) {
            System.out.println((i + 1) + "\t" + AccountStatus.values()[i]);
        }
    }
}
