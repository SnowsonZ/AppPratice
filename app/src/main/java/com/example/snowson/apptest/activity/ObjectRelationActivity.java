package com.example.snowson.apptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.ob.Customer;
import com.example.snowson.apptest.bean.ob.MyObjectBox;
import com.example.snowson.apptest.bean.ob.Order;
import com.example.snowson.apptest.bean.ob.Student;
import com.example.snowson.apptest.bean.ob.Teacher;
import com.example.snowson.apptest.bean.ob.TreeNode;

import java.util.List;

import io.objectbox.BoxStore;

public class ObjectRelationActivity extends AppCompatActivity {

    private BoxStore mBoxStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_relation);
        initObject();
        initData();
    }

    private void initData() {
        Customer customer = new Customer();
        customer.setName("customer one");
        customer.setVip(false);
        Order order = new Order();
        order.setDate("2018/01/17");
        order.setText("mark");
        //order添加customer依赖
        order.customer.setTarget(customer);
        //ToOne 1:1
        //返回该操作的唯一表示
        long orderId = mBoxStore.boxFor(Order.class).put(order);
        Order orderResult = mBoxStore.boxFor(Order.class).get(orderId);
        Customer customerResult = orderResult.customer.getTarget();

        //移除依赖关系
        order.customer.setTarget(null);
        mBoxStore.boxFor(Order.class).put(order);

        //add ToMany 1:N
        Order order1 = new Order();
        customer.orders.add(order);
        customer.orders.add(order1);
        long customerId = mBoxStore.boxFor(Customer.class).put(customer);

        Customer customer1 = mBoxStore.boxFor(Customer.class).get(customerId);
        for (Order item : customer1.orders) {
            if (!TextUtils.isEmpty(item.getText())) {
                customer1.orders.remove(item);
            }
        }
        //update db

        //add M:N
        Student student1 = new Student();
        Student student2 = new Student();
        Teacher teacher1 = new Teacher();
        Teacher teacher2 = new Teacher();

        student1.teachers.add(teacher1);
        student1.teachers.add(teacher2);
        student2.teachers.add(teacher2);
        mBoxStore.boxFor(Student.class).put(student1, student2);

        Student student1Result = mBoxStore.boxFor(Student.class).get(student1.id);
        for (Teacher teacher : student1Result.teachers) {
            if (teacher.getId() == 0) {
                student1Result.teachers.remove(teacher);
            }
        }
        mBoxStore.boxFor(Student.class).put(student1Result);


        //tree relation
        TreeNode rootNode = new TreeNode();
        TreeNode parent = rootNode.parent.getTarget();
        List<TreeNode> children =  rootNode.children;
    }

    private void initObject() {
        mBoxStore = MyObjectBox.builder().androidContext(this).build();
    }
}
