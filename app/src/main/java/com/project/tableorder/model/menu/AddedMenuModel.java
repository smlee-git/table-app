package com.project.tableorder.model.menu;

import java.io.Serializable;

public class AddedMenuModel implements Serializable {
    private int count;
    private MenuModel menuModel;

    public AddedMenuModel(int count, MenuModel menuModel) {
        this.count = count;
        this.menuModel = menuModel;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }
}
