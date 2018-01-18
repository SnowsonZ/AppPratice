package com.example.snowson.apptest.bean.ob;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Created by snowson on 18-1-17.
 */
@Entity
public class Order {
    @Id
    public long id;
    String date;
    String text;
    public ToOne<Customer> customer;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
