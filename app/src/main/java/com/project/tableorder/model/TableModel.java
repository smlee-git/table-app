package com.project.tableorder.model;

import java.io.Serializable;

// 테이블 모델
public class TableModel implements Serializable {
    private int id;
    private int fk_store_id;
    private int table_no;
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

    public int getTable_no() {
        return table_no;
    }

    public void setTable_no(int table_no) {
        this.table_no = table_no;
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
        return "TableModel{" +
                "id=" + id +
                ", fk_store_id=" + fk_store_id +
                ", table_no=" + table_no +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
