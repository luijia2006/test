package com.we.tools.test;

import java.io.*;

public class TestSerialize {


    public static void main(String[] args) throws Exception {
//        out();
        test();
    }

    private static void test() throws Exception {
        String s = "nihao";
        FileOutputStream fs = new FileOutputStream("foo.ser");
        ObjectOutputStream os =  new ObjectOutputStream(fs);
        os.writeUTF(s);
        os.close();
    }

    private static void out() {
        Box myBox = new Box();
        myBox.setWidth(50);
        myBox.setHeight(30);

        try{
            FileOutputStream fs = new FileOutputStream("foo.ser");
            ObjectOutputStream os =  new ObjectOutputStream(fs);
            os.writeObject(myBox);
            os.close();

            FileInputStream fi = new FileInputStream("foo.ser");
            ObjectInputStream oi = new ObjectInputStream(fi);
            Box o = (Box)oi.readObject();
            System.out.println(o.getWidth());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
