package com.wsrgen.utils;

import java.util.List;

public class DevUtils {
    public static <T> void printList(String caption, List<T> list){
        System.out.println("----"+caption+"------");
        for (T t : list) {
            System.out.println(t);
        }
        System.out.println("---------------------");
    }
}
