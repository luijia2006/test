package com.we.basics.random;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 随机数
 */
public class RandomUtil {
    /**
     * 生成随机数
     * @param num
     * @return
     */
    public static String genRandomNum(int num){
        int  maxNum = 36;
        int i;
        int count = 0;
        char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < num){
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }
        return pwd.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        Map<String, Integer> map = new HashMap<>();
        int count = 1;
        while (true) {
            String s = genRandomNum(4);
            Integer integer = map.get(s)==null?0:map.get(s);
            map.put(s, integer + 1);
            count++;
            if (integer!=0) {
                System.out.println(count+"===="+integer);
                break;
            }

        }

    }

}
