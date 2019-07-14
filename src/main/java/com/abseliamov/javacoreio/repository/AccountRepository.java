package com.abseliamov.javacoreio.repository;

import com.abseliamov.javacoreio.model.Account;

public interface AccountRepository extends GenericRepository<Account, Long> {

//    Account checkAccount(Long id);

    boolean update(Account account);
}
