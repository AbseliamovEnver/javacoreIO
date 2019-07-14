package com.abseliamov.javacoreio.view;

import com.abseliamov.javacoreio.utils.PrintMenu;
import com.abseliamov.javacoreio.utils.ReadInputData;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MainMenu {
    public long mainMenu() {
        Map<Integer, String> mainMenu = new TreeMap<>();

        mainMenu.put(1, "Developers");
        mainMenu.put(2, "Skills");
        mainMenu.put(3, "Accounts");
        mainMenu.put(0, "Exit");

        Set<Map.Entry<Integer, String>> mainMenuSet = mainMenu.entrySet();

        PrintMenu.printMenu(mainMenuSet, "\t M A I N  ", 0, 3);
        long select = ReadInputData.readInputData(0, 3);

        if (select == 0)
            System.out.println(" THANKS FOR USING OUR APPLICATION!");

        return select;
    }

    public void start() {
        DeveloperView developerViewMenu = new DeveloperView();
        SkillView skillViewMenu = new SkillView();
        AccountView accountViewMenu = new AccountView();

        while (true) {
            long select = mainMenu();
            switch ((int) select) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    developerViewMenu.developerView();
                    break;
                case 2:
                    skillViewMenu.skillView();
                    break;
                case 3:
                    accountViewMenu.accountView();
                    break;
                default:
                    continue;
            }
        }
    }
}
