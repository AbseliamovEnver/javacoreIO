package com.abseliamov.module14.view;

import com.abseliamov.module14.controller.AccountController;
import com.abseliamov.module14.model.Account;
import com.abseliamov.module14.model.AccountStatus;
import com.abseliamov.module14.utils.PrintMenu;
import com.abseliamov.module14.utils.ReadInputData;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AccountView {
    public long accountMenu() {
        Map<Integer, String> accountMenu = new TreeMap<>();

        accountMenu.put(1, "Create account");
        accountMenu.put(2, "Get account by ID");
        accountMenu.put(3, "Get account by login");
        accountMenu.put(4, "Get all accounts");
        accountMenu.put(5, "Update account");
        accountMenu.put(6, "Delete account");
        accountMenu.put(0, "Main menu.");

        Set<Map.Entry<Integer, String>> accountMenuSet = accountMenu.entrySet();

        PrintMenu.printMenu(accountMenuSet, "\tA C C O U N T S  ", 0, 6);
        long select = ReadInputData.readInputData(0, 6);

        return select;
    }

    public void accountView() {
        AccountController accountController = new AccountController();
        boolean marker = true;
        boolean menu = true;
        long select;

        while (marker) {
            select = menu ? accountMenu() : 1;
            switch ((int) select) {
                case 0:
                    marker = false;
                    break;
                case 1:
                    System.out.println("If you want to create a new account enter \'Y\' or other key to continue:");
                    if (ReadInputData.readInputString().equalsIgnoreCase("Y")) {
                        System.out.println("Enter account login:");
                        String login = ReadInputData.readInputString();
                        System.out.println("Enter account password:");
                        String password = ReadInputData.readInputString();
                        accountController.getListStatus();
                        System.out.println("Chose account status:");
                        long idStatus = ReadInputData.readInputData(0, AccountStatus.values().length - 1);
                        accountController.add(login, password, idStatus);
                        menu = false;
                    } else {
                        menu = true;
                    }
                    break;
                case 2:
                    if (accountController.getListAccounts() != null) {
                        System.out.println("Enter account ID or enter \'-1\' to continue: ");
                        long id;
                        while ((id = ReadInputData.readInputData(-1, Long.MAX_VALUE)) != -1) {
                            Account account = accountController.getById(id);
                            if (account != null) {
                                System.out.println("Account with id: \'" + id + "\' have login \'"
                                        + account.getLogin() + "\' and status \'" + account.getStatus() + "\'.");
                                System.out.println("Select other account id or enter \'-1\' to continue:");
                            } else {
                                System.out.println("Account with id \'" + id + "\' not found.");
                                System.out.println("Select correct account id or enter \'-1\' to continue:");
                            }
                        }
                    } else {
                        System.out.println("Please create new account.");
                        menu = false;
                    }
                    break;
                case 3:
                    System.out.println("Accounts list:");
                    if (accountController.getListAccounts() != null) {
                        System.out.println("Enter account login or enter \'-1\' to continue: ");
                        String loginAcc;
                        while (!(loginAcc = ReadInputData.readInputString()).equals("-1")) {
                            Account account = accountController.getByName(loginAcc);
                            if (account != null) {
                                System.out.println("Account with login: \'" + loginAcc + "\' have id \'"
                                        + account.getId() + "\' and status \'" + account.getStatus() + "\'.");
                                System.out.println("Select other account login or enter \'-1\' to continue:");
                            } else {
                                System.out.println("Account with login \'" + loginAcc + "\' not found.");
                                System.out.println("Select correct account login or enter \'-1\' to continue:");
                            }
                        }
                    } else {
                        System.out.println("Please create new account.");
                        menu = false;
                    }
                    break;
                case 4:
                    System.out.println("List accounts: ");
                    if (accountController.getListAccounts() == null) {
                        System.out.println("Please create new account.");
                        menu = false;
                    }
                    break;
                case 5:
                    System.out.println("List of all ACCOUNTS:");
                    if (accountController.getListAccounts() != null) {
                        System.out.println("Choose account id to update or enter \'-1\' to continue: ");
                        long accountId;
                        while ((accountId = ReadInputData.readInputData(-1, Long.MAX_VALUE)) != -1) {
                            System.out.println("Enter password:");
                            String password = ReadInputData.readInputString();
                            if (accountController.checkAccount(accountId, password)) {
                                System.out.println("Enter new login or press \'ENTER\' to leave old login: ");
                                String newLogin = ReadInputData.readInputString();
                                System.out.println("Enter new password or press \'ENTER\' to leave old password: ");
                                String newPassword = ReadInputData.readInputString();
                                accountController.getListStatus();
                                System.out.println("Chose new account STATUS: ");
                                long newStatus = ReadInputData.readInputData(0, AccountStatus.values().length - 1);
                                accountController.update(accountId, newLogin, newPassword, newStatus);
                                System.out.println("Enter account id to update or enter \'-1\' to continue:");
                            } else {
                                System.out.println("Account with that id \'" + accountId + "\' and entered password \'"
                                        + password + "\' not found.\nmay be entered incorrect password.");
                                System.out.println("Select correct account id and enter correct password or enter \'-1\' to continue:");
                            }
                        }
                    } else {
                        System.out.println("Please create new account.");
                        menu = false;
                    }
                    break;
                case 6:
                    System.out.println("Accounts list:");
                    if (accountController.getListAccounts() != null) {
                        System.out.println("Enter account ID to delete or \'-1\' to continue: ");
                        long idAccount;
                        while ((idAccount = ReadInputData.readInputData(-1, Long.MAX_VALUE)) != -1) {
                            Account account = accountController.delete(idAccount);
                            if (account != null) {
                                System.out.println("Account with id: \'" + idAccount + "\', login \'"
                                        + account.getLogin() + "\' and status "
                                        + account.getStatus() + " deleted successfully.");
                                System.out.println("Select other account id or enter \'-1\' to continue:");
                            } else {
                                System.out.println("Account with id \'" + idAccount + "\' not found.");
                                System.out.println("Select correct account id or enter \'-1\' to continue:");
                            }
                        }
                    } else {
                        System.out.println("Please create new account.");
                        menu = false;
                    }
                    break;
                default:
                    continue;
            }
        }
    }
}
