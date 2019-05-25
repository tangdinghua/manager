package com.trip.base.entity;

import java.util.List;

public class Busi {

    private String table;

    private List<Column> items;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<Column> getItems() {
        return items;
    }

    public void setItems(List<Column> items) {
        this.items = items;
    }
}
