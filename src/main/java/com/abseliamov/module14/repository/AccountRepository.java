package com.abseliamov.module14.repository;

import com.abseliamov.module14.model.Account;

public interface AccountRepository extends GenericRepository<Account, Long> {

    Account checkAccount(Long id, String password);

    boolean update(Account account);
}
