package com.project.tableorder.model.receipt;

import com.project.tableorder.model.menu.AddedMenuModel;

import java.io.Serializable;
import java.util.List;

public class ReceiptModel implements Serializable {
    private List<AddedMenuModel> addedMenuModels;

    public ReceiptModel(List<AddedMenuModel> addedMenuModels) {
        this.addedMenuModels = addedMenuModels;
    }

    public List<AddedMenuModel> getAddedMenuModels() {
        return addedMenuModels;
    }

    public void setAddedMenuModels(List<AddedMenuModel> addedMenuModels) {
        this.addedMenuModels = addedMenuModels;
    }
}
