package com.abseliamov.javacoreio.view;

import com.abseliamov.javacoreio.controller.AccountController;
import com.abseliamov.javacoreio.model.Account;
import com.abseliamov.javacoreio.model.AccountStatus;
import com.abseliamov.javacoreio.utils.PrintMenu;
import com.abseliamov.javacoreio.utils.ReadInputData;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AccountView {
    public long accountMenu() {
        Map<Integer, String> accountMenu = new TreeMap<>();

        accountMenu.put(1, "Create account");
        accountMenu.put(2, "Get account by ID");
        accountMenu.put(3, "Get account by name");
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
                        accountController.getListStatus();
                        System.out.println("Chose account status or enter \'0\' to continue:");
                        long accountId;
                        while ((accountId = ReadInputData.readInputData(0, AccountStatus.values().length)) != 0) {
                            accountController.add(accountId);
                            System.out.println("Chose account status or enter \'0\' to continue:");
                        }
                        menu = false;
                    } else {
                        menu = true;
                    }
                    break;
                case 2:
                    if (accountController.getListAccounts() != null) {
                        System.out.println("Enter account ID or enter \'0\' to continue: ");
                        long id;
                        while ((id = ReadInputData.readInputData(0, AccountStatus.values().length)) != 0) {
                            Account account = accountController.getById(id);
                            if (account != null) {
                                System.out.println("Account with id: \'" + id + "\' have status \'" + account.getStatus() + "\'.");
                                System.out.println("Select other account id or enter \'0\' to continue:");
                            } else {
                                System.out.println("Account with id \'" + id + "\' not found.");
                                System.out.println("Select correct account id or enter \'0\' to continue:");
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
                        System.out.println("Enter account status or enter \'0\' to continue: ");
                        String status;
                        while (!(status = ReadInputData.readInputString()).equalsIgnoreCase("0")) {
                            Account account = accountController.getByName(status);
                            if (account != null) {
                                System.out.println("Account with status: \'" + status + "\' have id \'"
                                        + account.getId() + "\'.");
                                System.out.println("Select other account status or enter \'0\' to continue:");
                            } else {
                                System.out.println("Account with status \'" + status + "\' not found.");
                                System.out.println("Select correct account status or enter \'0\' to continue:");
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
                        System.out.println("Choose account id to update or enter \'0\' to continue: ");
                        long accountId;
                        while ((accountId = ReadInputData.readInputData(0, AccountStatus.values().length)) != 0) {
/*                            if (accountController.getById(accountId) != null) {
//                                String newPassword = ReadInputData.readInputString();
                                accountController.getListStatus();
                                System.out.println("Chose new account STATUS: ");
                                long newAccountId = ReadInputData.readInputData(0, AccountStatus.values().length);
                                accountController.update(accountId, newAccountId);
                                System.out.println("Enter account id to update or enter \'0\' to continue:");
                            } else {
                                System.out.println("Account with that id \'" + accountId + "\' not found.");
                                System.out.println("Select correct account id or enter \'0\' to continue:");
                            }
  */                      }
                    } else {
                        System.out.println("Please create new account.");
                        menu = false;
                    }
                    break;
                case 6:
                    System.out.println("Accounts list:");
                    if (accountController.getListAccounts() != null) {
                        System.out.println("Enter account ID to delete or \'0\' to continue: ");
                        long idAccount;
                        while ((idAccount = ReadInputData.readInputData(0, AccountStatus.values().length)) != 0) {
                            accountController.delete(idAccount);
                            System.out.println("Select other account id or enter \'0\' to continue:");
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
