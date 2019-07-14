package com.abseliamov.javacoreio.repository;

import com.abseliamov.javacoreio.model.Account;
import com.abseliamov.javacoreio.model.AccountStatus;
import com.abseliamov.javacoreio.utils.CheckFile;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class JavaIOAccountRepositoryImpl implements AccountRepository {
    public static final String ACCOUNTS_FILE = "src/main/resources/accounts.txt";

    @Override
    public void add(Account account) {
        boolean addAccount = true;
        File file = CheckFile.checkFileExists(ACCOUNTS_FILE);
        String data;

        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNTS_FILE, true))) {
            if (file.length() != 0) {
                while ((data = reader.readLine()) != null) {
                    String[] skillData = data.split(",");
                    if (Long.parseLong(skillData[0]) == account.getId())
                        addAccount = false;
                }
            }
            if (addAccount) {
//                account.setId(GetID.getID(ACCOUNTS_FILE));
                writer.write(account.getId() + "," + account.getStatus() + "\n");
                System.out.println("Account with id \'" + account.getId()
                        + "\' and status \'" + account.getStatus() + "\' added successful");
                writer.flush();
            } else System.out.println("This status already exists");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception read/write file in method add account: " + e);
        }
    }

    @Override
    public Account getById(Long id) {
        File file = CheckFile.checkFileExists(ACCOUNTS_FILE);
        String data;

        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            if (file.length() != 0) {
                while ((data = reader.readLine()) != null) {
                    String[] accountData = data.split(",");
                    if (Long.parseLong(accountData[0]) == id)
                        return new Account(id, (AccountStatus.valueOf(accountData[1])));
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
    public Account getByName(String status) {
        File file = CheckFile.checkFileExists(ACCOUNTS_FILE);
        String data;

        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            if (file.length() != 0) {
                while ((data = reader.readLine()) != null) {
                    String[] accountData = data.split(",");
                    if (accountData[1].equalsIgnoreCase(status))
                        return new Account(Long.parseLong(accountData[0]), AccountStatus.valueOf(accountData[1]));
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
        File file = CheckFile.checkFileExists(ACCOUNTS_FILE);
        Set<Account> accounts = new HashSet<>();
        String data;

        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            if (file.length() != 0) {
                while ((data = reader.readLine()) != null) {
                    String[] accoundData = data.split(",");
                    accounts.add(new Account(Long.parseLong(accoundData[0]), AccountStatus.valueOf(accoundData[1])));
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
        File file = CheckFile.checkFileExists(ACCOUNTS_FILE);
        Set<Account> accounts = new HashSet<>();
        boolean updateAccount = false;
        String data;

        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            if (file.length() != 0) {
                while ((data = reader.readLine()) != null) {
                    String[] accountData = data.split(",");
                    if (Long.parseLong(accountData[0]) == account.getId()) {
                        accounts.add(new Account(Long.parseLong(accountData[0]), account.getStatus()));
                        updateAccount = true;
                        continue;
                    }
                    accounts.add(new Account(Long.parseLong(accountData[0]), AccountStatus.valueOf(accountData[1])));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception reading file in method update account: " + e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNTS_FILE, false))) {
            if (updateAccount) {
                for (Account accountData : accounts)
                    writer.write(accountData.getId() + "," + accountData.getStatus() + "\n");
                return true;
            }
        } catch (IOException e) {
            System.out.println("Exception writing file in method update account: " + e);
        }
        return false;
    }

    @Override
    public void delete(Long id) {
        File file = CheckFile.checkFileExists(ACCOUNTS_FILE);
        Set<Account> accounts = new HashSet<>();
        String data;
        boolean accountDelete = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            if (file.length() != 0) {
                while ((data = reader.readLine()) != null) {
                    String[] accountData = data.split(",");
                    if (Long.parseLong(accountData[0]) == id) {
                        accountDelete = true;
                        System.out.println("Account with id: \'" + id + "\', and status "
                                + AccountStatus.valueOf(accountData[1]) + " deleted successfully.");
                        continue;
                    }
                    accounts.add(new Account(Long.parseLong(accountData[0]), AccountStatus.valueOf(accountData[1])));
                }
                if (!accountDelete)
                    System.out.println("Account with id \'" + id + "\' not found.");
            }
        } catch (IOException e) {
            System.out.println("Exception reading from file in method delete account: " + e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNTS_FILE, false))) {
            for (Account account : accounts)
                writer.write(account.getId() + "," + account.getStatus() + "\n");
        } catch (IOException e) {
            System.out.println("Exception writing file in method delete account: " + e);
        }
    }
}
