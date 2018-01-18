package com.example.snowson.apptest.bean.ob;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

/**
 * Created by snowson on 18-1-17.
 */
@Entity
public class Student {
    @Id
    public long id;
    public ToMany<Teacher> teachers;
}
