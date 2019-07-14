package com.abseliamov.javacoreio.view;

import com.abseliamov.javacoreio.controller.AccountController;
import com.abseliamov.javacoreio.controller.DeveloperController;
import com.abseliamov.javacoreio.controller.SkillController;
import com.abseliamov.javacoreio.model.Account;
import com.abseliamov.javacoreio.model.Developer;
import com.abseliamov.javacoreio.model.Skill;
import com.abseliamov.javacoreio.utils.PrintMenu;
import com.abseliamov.javacoreio.utils.ReadInputData;

import java.util.*;

public class DeveloperView {
    public long developerMenu() {
        Map<Integer, String> developerMenu = new TreeMap<>();

        developerMenu.put(1, "Create developer");
        developerMenu.put(2, "Get developer by ID");
        developerMenu.put(3, "Get developer by name");
        developerMenu.put(4, "Get all developers");
        developerMenu.put(5, "Update developer");
        developerMenu.put(6, "Delete developer by ID");
        developerMenu.put(0, "Main menu");

        Set<Map.Entry<Integer, String>> mainMenuSet = developerMenu.entrySet();

        PrintMenu.printMenu(mainMenuSet, " D E V E L O P E R S  ", 0, 6);
        long select = ReadInputData.readInputData(0, 6);

        return select;
    }

    public void developerView() {
        DeveloperController developerController = new DeveloperController();
        SkillController skill = new SkillController();
        AccountController account = new AccountController();
        Set<Account> accounts;
        Account accountItem = null;
        Set<Skill> skills = new TreeSet<>();
        boolean marker = true;
        boolean menu = true;
        long select;

        while (marker) {
            select = menu ? developerMenu() : 1;
            switch ((int) select) {
                case 0:
                    marker = false;
                    break;
                case 1:
                    System.out.println("If you want to create a new developer enter \'Y\' or other key to continue:");
                    if (ReadInputData.readInputString().equalsIgnoreCase("Y")) {
                        System.out.println("Enter developer first name:");
                        String firstName = ReadInputData.readInputString();
                        System.out.println("Enter developer last name:");
                        String lastName = ReadInputData.readInputString();
                        System.out.println("List skills: \nID\tSKILLS");
                        Set<Skill> skillSet;
                        if ((skillSet = skill.getListSkills()) == null) {
                            System.out.println("Please create a list of skills to create a developer.");
                            break;
                        } else
                            for (Skill skillItem : skillSet)
                                System.out.println(skillItem.getId() + "\t" + skillItem.getSkillName());

                        System.out.println("Select developer skills or enter \'0\': ");
                        long idSkill;
                        while ((idSkill = ReadInputData.readInputData(0, Long.MAX_VALUE)) != 0) {
                            boolean status = false;
                            for (Skill skillItem : skillSet) {
                                if (skillItem.getId() == idSkill) {
                                    skills.add(skillItem);
                                    System.out.println("Skill with id " + idSkill + " added.");
                                    status = true;
                                    break;
                                }
                            }
                            if (status) {
                                System.out.println("Add more skills or click \'0\'");
                                continue;
                            } else {
                                System.out.println("Skill with id \'" + idSkill + "\' not found.");
                                System.out.println("Choose id skill or enter \'0\':");
                            }
                        }
                        if ((accounts = account.getListAccounts()) == null) {
                            System.out.println("Please create a list of accounts to create a developer.");
                            break;
                        }
                        long idAccount;
                        System.out.println("Choose id account or enter \'0\':");
                        while ((idAccount = ReadInputData.readInputData(0, Long.MAX_VALUE)) != 0) {
                            boolean flag = false;
                            for (Account acc : accounts) {
                                if (acc.getId() == idAccount) {
                                    accountItem = acc;
                                    System.out.println("Account with id \'" + idAccount + "\' added.");
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag)
                                break;
                            else {
                                System.out.println("Account with id \'" + idAccount + "\' not found.");
                                System.out.println("Choose id account or enter \'0\':");
                            }
                        }
                        developerController.add(0, firstName, lastName, skills, accountItem);
                        menu = false;
                    } else
                        menu = true;
                    break;
                case 2:
                    if (developerController.getListDeveloper() != null) {
                        System.out.println("Enter developer ID or enter \'0\' to continue: ");
                        long id;
                        while ((id = ReadInputData.readInputData(0, Long.MAX_VALUE)) != 0) {
                            Developer developer = developerController.getById(id);
                            if (developer != null) {
                                System.out.println("Developer with id = \'" + id + "\' have:\nname = \'"
                                        + developer.getFirstName() + "\'\nsurname = \'" + developer.getLastName()
                                        + "\'\nskill(s):\n\t" + developer.getSkills() + "\naccount:\n"
                                        + developer.getAccount());
                                System.out.println("Select other developer id or enter \'0\' to continue:");
                            } else {
                                System.out.println("Developer with id \'" + id + "\' not found.");
                                System.out.println("Select correct developer id or enter \'0\' to continue:");
                            }
                        }
                    } else {
                        System.out.println("Please create new developer.");
                        menu = false;
                    }
                    break;
                case 3:
                    if (developerController.getListDeveloper() != null) {
                        System.out.println("Enter developer name or enter \'0\' to continue: ");
                        String nameDeveloper;
                        while (!(nameDeveloper = ReadInputData.readInputString()).equals("0")) {
                            Developer developer = developerController.getByName(nameDeveloper);
                            if (developer != null) {
                                System.out.println("Developer with name = \'" + developer.getFirstName() + "\' have:\nid = \'"
                                        + developer.getId() + "\'\nsurname = \'" + developer.getLastName()
                                        + "\'\nskill(s):\n\t" + developer.getSkills() + "\naccount:\n\t"
                                        + developer.getAccount());
                                System.out.println("Select other developer name or enter \'0\' to continue:");
                            } else {
                                System.out.println("Developer with name \'" + nameDeveloper + "\' not found.");
                                System.out.println("Select correct developer name or enter \'0\' to continue:");
                            }
                        }
                    } else {
                        System.out.println("Please create new developer.");
                        menu = false;
                    }
                    break;
                case 4:
                    System.out.println("List of all DEVELOPERS:");
                    if (developerController.getListDeveloper() == null) {
                        System.out.println("Please create new developer.");
                        menu = false;
                    }
                    break;
                case 5:
                    System.out.println("List of all DEVELOPERS:");
                    if (developerController.getListDeveloper() != null) {
                        System.out.println("Choose developer id to update or enter \'0\' to continue: ");
                        long developerId;
                        while ((developerId = ReadInputData.readInputData(0, Long.MAX_VALUE)) != 0) {
                            if (developerController.checkDeveloper(developerId)) {
                                System.out.println("Enter new name or press \'ENTER\' to leave old name: ");
                                String newName = ReadInputData.readInputString();
                                System.out.println("Enter new surname or press \'ENTER\' to leave old surname: ");
                                String newSurname = ReadInputData.readInputString();
                                System.out.println("If you want to add new skill enter \'Y\' or any other key to continue.");
                                if ((ReadInputData.readInputString()).equalsIgnoreCase("Y")) {
                                    boolean status = false;
                                    long skillNewId;
                                    Set<Skill> skillSet1 = skill.getListSkills();
                                    System.out.println("Choose new id skill or enter \'0\' to continue: ");
                                    while ((skillNewId = ReadInputData.readInputData(0, Long.MAX_VALUE)) != 0) {
                                        status = developerController.checkDeveloperSkill(skillSet1, skillNewId, developerId);
                                        if (status) {
                                            System.out.println("Skill with id " + skillNewId + " added.");
                                            System.out.println("Choose new id skill or enter \'0\' to continue:");
                                        } else {
                                            System.out.println("Choose new id skill or enter \'0\' to continue:");
                                        }
                                    }
                                    System.out.println("Choose developer id to update or enter \'0\' to continue:");
                                }
                                long newAccountId = 0;
                                Account newAccount = developerController.getById(developerId).getAccount();
                                System.out.println("If you want to change account status enter \'Y\' or any other key to continue.");
                                if ((ReadInputData.readInputString()).equalsIgnoreCase("Y")) {
                                    System.out.println("You status is: " + newAccount.getStatus());
                                    System.out.println("Chose new id new status: ");
                                    long sizeAccountList = account.getListAccounts().size();
                                    newAccountId = ReadInputData.readInputData(1, sizeAccountList);
                                    newAccount = account.getById(newAccountId);
                                }
                                String developerName = newName.isEmpty()
                                        ? developerController.getById(developerId).getFirstName() : newName;
                                String developerLastname = newSurname.isEmpty()
                                        ? developerController.getById(developerId).getLastName() : newSurname;

                                if (!newName.isEmpty() || !newSurname.isEmpty() || newAccount != null)
                                    developerController.update(developerId, developerName, developerLastname, newAccount);
                                System.out.println("Enter \'0\' to continue:");
                            } else {
                                System.out.println("Developer with id \'" + developerId + "\' not found.");
                                System.out.println("Select correct developer id or enter \'0\' to continue:");
                            }
                        }
                    } else {
                        System.out.println("Please create new developer.");
                        menu = false;
                    }
                    break;
                case 6:
                    System.out.println("List of all DEVELOPERS:");
                    if (developerController.getListDeveloper() != null) {
                        System.out.println("Choose developer id to remove or enter \'0\' to continue: ");
                        long id;
                        while ((id = ReadInputData.readInputData(0, Long.MAX_VALUE)) != 0) {
                            developerController.delete(id);
                            System.out.println("Select other developer id or enter \'0\' to continue:");
                        }
                    } else {
                        System.out.println("Please create new developer.");
                        menu = false;
                    }
                    break;
                default:
                    continue;
            }
        }
    }
}
