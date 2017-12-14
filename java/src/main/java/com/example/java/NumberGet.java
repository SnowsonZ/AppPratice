package com.example.java;

import java.util.ArrayList;
import java.util.List;

public class NumberGet {
    public static void main(String[] args) {
        String s = "aba13az12bab第三方缩放24缩放缩放12缩放缩放fsfsfsd1993年6月29号";
        List<String> ss = new ArrayList<String>();
        for (String sss : s.replaceAll("[^0-9]", ",").split(",")) {
            if (sss.length() > 0)
                ss.add(sss);
        }
        System.out.print(ss);
    }
}
