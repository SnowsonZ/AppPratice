package com.example.snowson.apptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.ob.MyObjectBox;
import com.example.snowson.apptest.bean.ob.Teacher;
import com.example.snowson.apptest.bean.ob.Teacher_;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;

public class ObjectBoxGeneralActivity extends AppCompatActivity {

    private BoxStore boxStore;
    private Box<Teacher> mTeacherBox;
    private Query<Teacher> mQueryTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_box);
        initObjectBox();
        optData();
    }

    private void optData() {
        mQueryTeacher = mTeacherBox.query().order(Teacher_.teacherId).build();
        //query
        mTeacherBox.query().equal(Teacher_.subject, "数学");
    }

    private void initObjectBox() {
        boxStore = MyObjectBox.builder().androidContext(this).build();
        mTeacherBox = boxStore.boxFor(Teacher.class);
    }

    //update data
    private void updateTeacher(int id, String value) {
        //quick method only by id
        Teacher teacher = mTeacherBox.get(id);

        //query by customer criteria 根据自定义规则查询
        List<Teacher> teachers = mTeacherBox.query()
                .equal(Teacher_.subject, "math")
                .greater(Teacher_.id, 2)
                .between(Teacher_.id, 3, 10)
                .contains(Teacher_.subject, "t")
                .build()
                .find();
    }

    //add data
    private void addTeacher() {
        Teacher teacher = new Teacher();
        teacher.setSubject("Math");
        teacher.setTeacherId("12345560324");
        mTeacherBox.put(teacher);
    }


    //根据id删除对应数据
    private void removeTeacher(long id) {
        mTeacherBox.remove(id);
    }

    //query data
    private void queryTeacher(long id) {
        mTeacherBox.get(id);
    }

    //对结果集的操作 ：min avg count等
    private void optQueryValue() {
        mQueryTeacher.property(Teacher_.teacherId).minDouble();
        mQueryTeacher.property(Teacher_.id).avg();
        mQueryTeacher.property(Teacher_.id).max();
        mQueryTeacher.property(Teacher_.id).sum();
    }
}
