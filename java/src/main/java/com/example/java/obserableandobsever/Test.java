package com.example.java.obserableandobsever;

import com.example.java.obserableandobsever.bean.TypeA;
import com.example.java.obserableandobsever.bean.TypeB;

/**
 * Created by snowson on 18-1-5.
 */

public class Test {
    public static void main(String[] args) {
        TypeA typeA = new TypeA();
        TypeB typeB = new TypeB();
        typeA.addObserver(typeB);
        typeA.setChange(10);
        System.out.println("typeA： " + typeA.num + ", typeB： " + typeB.number);

    }
}
