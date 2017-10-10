package vsu.kurs3.att1.task3;

import vsu.kurs3.att1.task3.menu.enums.EMenuCode;
import vsu.kurs3.att1.task3.menu.logics.MenuFabric;
import vsu.kurs3.att1.task3.menu.specialMenus.menuCore.ParentMenu;
import vsu.kurs3.att1.task3.menu.structuralMenus.MenuLogic;
import vsu.kurs3.att1.task3.structures.course.CoursesOperator;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Menu();
    }

    private static void Menu() {
        List<ParentMenu> menus = new LinkedList<>();
        MenuFabric mf = new MenuFabric();
        EMenuCode[] codes = EMenuCode.values();

        for (EMenuCode code : codes) menus.add(mf.createMenu(code));

        MenuLogic menuLogic = new MenuLogic(new CoursesOperator(), menus);

        boolean flag = true;
        while(flag){
            flag = menuLogic.start();
        }
    }
}