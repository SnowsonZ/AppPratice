package com.example.snowson.apptest.bean.ob;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

/**
 * Created by snowson on 18-1-17.
 */

@Entity
public class Customer {
    @Id
    public long id;
    @Backlink
    public ToMany<Order> orders;
    String name;
    boolean isVip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
}
