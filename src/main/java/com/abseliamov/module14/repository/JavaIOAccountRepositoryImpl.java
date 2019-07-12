package com.abseliamov.module14.repository;

import com.abseliamov.module14.model.Account;
import com.abseliamov.module14.model.AccountStatus;
import com.abseliamov.module14.utils.GetID;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class JavaIOAccountRepositoryImpl implements AccountRepository {

    @Override
    public void add(Account account) {
        boolean addAccount = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(Account.ACCOUNTS_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(Account.ACCOUNTS_FILE, true))) {
            File file = new File(Account.ACCOUNTS_FILE);
            if (file.exists() && file.length() != 0) {
                String data;
                while ((data = reader.readLine()) != null) {
                    String[] skillData = data.split("\t");
                    if (skillData[1].equalsIgnoreCase(account.getLogin()))
                        addAccount = false;
                }
            }
            if (addAccount) {
                account.setId(GetID.getID(Account.ACCOUNTS_FILE));
                writer.write(account.getId() + "\t" + account.getLogin() + "\t"
                        + account.getPassword() + "\t" + account.getStatus() + "\n");
                writer.flush();
            } else System.out.println("This login already exists");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception read/write file in method add account: " + e);
        }
    }

    @Override
    public Account getById(Long id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(Account.ACCOUNTS_FILE))) {
            File file = new File(Account.ACCOUNTS_FILE);
            if (file.exists() && file.length() != 0) {
                String data;
                while ((data = reader.readLine()) != null) {
                    String[] accountData = data.split("\t");
                    if (Long.parseLong(accountData[0]) == id)
                        return new Account(id, accountData[1], accountData[2], (AccountStatus.valueOf(accountData[3])));
                }
            } else System.out.println("File with accounts is empty");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception reading file in method get account by id: " + e);
        }
        return null;
    }

    @Override
    public Account getByName(String login) {
        try (BufferedReader reader = new BufferedReader(new FileReader(Account.ACCOUNTS_FILE))) {
            File file = new File(Account.ACCOUNTS_FILE);
            if (file.exists() && file.length() != 0) {
                String data;
                while ((data = reader.readLine()) != null) {
                    String[] accountData = data.split("\t");
                    if (accountData[1].equalsIgnoreCase(login))
                        return new Account(Long.parseLong(accountData[0]),
                                accountData[1], accountData[2], AccountStatus.valueOf(accountData[3]));
                }
            } else System.out.println("File with accounts is empty");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception reading file in method get account by name: " + e);
        }
        return null;
    }

    @Override
    public Set<Account> getAll() {
        Set<Account> accounts = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Account.ACCOUNTS_FILE))) {
            File file = new File(Account.ACCOUNTS_FILE);
            if (file.exists() && file.length() != 0) {
                String data;
                while ((data = reader.readLine()) != null) {
                    String[] accoundData = data.split("\t");
                    accounts.add(new Account(Long.parseLong(accoundData[0]),
                            accoundData[1], accoundData[2], AccountStatus.valueOf(accoundData[3])));
                }
                return accounts;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception reading file in method get all accounts: " + e);
        }
        return null;
    }

    @Override
    public boolean update(Account account) {
        Set<Account> accounts = new HashSet<>();
        boolean updateAccount = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(Account.ACCOUNTS_FILE))) {
            File file = new File(Account.ACCOUNTS_FILE);
            if (file.exists() && file.length() != 0) {
                String data;
                while ((data = reader.readLine()) != null) {
                    String[] accountData = data.split("\t");
                    if (Long.parseLong(accountData[0]) == account.getId()) {
                        if (account.getLogin().isEmpty())
                            account.setLogin(accountData[1]);
                        if (account.getPassword().isEmpty())
                            account.setPassword(accountData[2]);

                        accounts.add(new Account(account.getId(),
                                account.getLogin(), account.getPassword(), account.getStatus()));
                        updateAccount = true;
                        continue;
                    }
                    accounts.add(new Account(Long.parseLong(accountData[0]),
                            accountData[1], accountData[2], AccountStatus.valueOf(accountData[3])));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception reading file in method update account: " + e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Account.ACCOUNTS_FILE, false))) {
            if (updateAccount) {
                for (Account accountData : accounts) {
                    writer.write(accountData.getId() + "\t" + accountData.getLogin() + "\t"
                            + accountData.getPassword() + "\t" + accountData.getStatus() + "\n");
                }
                return true;
            }
        } catch (IOException e) {
            System.out.println("Exception writing file in method update account: " + e);
        }
        return false;
    }

    @Override
    public Account delete(Long id) {
        Set<Account> accounts = new HashSet<>();
        Account accountDelete = null;
        String data;

        try (BufferedReader reader = new BufferedReader(new FileReader(Account.ACCOUNTS_FILE))) {
            File file = new File(Account.ACCOUNTS_FILE);
            if (file.exists() && file.length() != 0) {
                while ((data = reader.readLine()) != null) {
                    String[] accountData = data.split("\t");
                    if (Long.parseLong(accountData[0]) == id) {
                        accountDelete = new Account(id, accountData[1],
                                accountData[2], AccountStatus.valueOf(accountData[3]));
                        continue;
                    }
                    accounts.add(new Account(Long.parseLong(accountData[0]),
                            accountData[1], accountData[2], AccountStatus.valueOf(accountData[3])));
                }
            }
        } catch (IOException e) {
            System.out.println("Exception reading from file in method delete account: " + e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Account.ACCOUNTS_FILE, false))) {
            for (Account account : accounts)
                writer.write(account.getId() + "\t"
                        + account.getLogin() + "\t" + account.getPassword() + "\t" + account.getStatus() + "\n");
        } catch (IOException e) {
            System.out.println("Exception writing file in method delete account: " + e);
        }
        return accountDelete;
    }

    @Override
    public Account checkAccount(Long id, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(Account.ACCOUNTS_FILE))) {
            File file = new File(Account.ACCOUNTS_FILE);
            if (file.exists() && file.length() != 0) {
                String data;
                while ((data = reader.readLine()) != null) {
                    String[] accountData = data.split("\t");
                    if (Long.parseLong(accountData[0]) == id && accountData[2].equals(password))
                        return new Account(id, accountData[1], accountData[2], (AccountStatus.valueOf(accountData[3])));
                }
            } else System.out.println("File with accounts is empty");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception reading file in method check account: " + e);
        }
        return null;
    }
}