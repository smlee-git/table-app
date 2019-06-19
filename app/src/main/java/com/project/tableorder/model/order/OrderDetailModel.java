package com.project.tableorder.model.order;

public class OrderDetailModel {
    private int id;
    private int fk_order_id;
    private int fk_menu_id;
    private int count;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_order_id() {
        return fk_order_id;
    }

    public void setFk_order_id(int fk_order_id) {
        this.fk_order_id = fk_order_id;
    }

    public int getFk_menu_id() {
        return fk_menu_id;
    }

    public void setFk_menu_id(int fk_menu_id) {
        this.fk_menu_id = fk_menu_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
