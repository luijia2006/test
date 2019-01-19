package com.we;

public class TestEmpty {

    public static void main(String[] args) {
        /*List list = CollectionUtil.list();
        if (Util.isEmpty(list)) {
            System.out.println("true");
        }*/
        test();
    }


    public static void test() {
        int gainScore=10;
        int count=8;
        int rightCount=3;
        System.out.println(((rightCount*100/count)*gainScore)/100);
    }

}


