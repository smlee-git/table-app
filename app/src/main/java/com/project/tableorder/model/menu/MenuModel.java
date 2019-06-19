package com.project.tableorder.model.menu;

import java.io.Serializable;

public class MenuModel implements Serializable {
    private int id;
    private int fk_store_id;
    private String poster;
    private String name;
    private int price;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_store_id() {
        return fk_store_id;
    }

    public void setFk_store_id(int fk_store_id) {
        this.fk_store_id = fk_store_id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "MenuModel{" +
                "id=" + id +
                ", fk_store_id=" + fk_store_id +
                ", poster='" + poster + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
