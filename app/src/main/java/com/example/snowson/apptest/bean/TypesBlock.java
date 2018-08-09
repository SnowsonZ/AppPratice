package com.example.snowson.apptest.bean;

import java.util.ArrayList;

public class TypesBlock {
    private ArrayList<MultiTypeBase> lists;
    private String header;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<MultiTypeBase> getLists() {
        return lists;
    }

    public void setLists(ArrayList<MultiTypeBase> lists) {
        this.lists = lists;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
