package com.we.tools.test;

import java.util.UUID;

public class CreateUuid {

    /**
     *      * 生成随机账号
     *      * @return
     *      
     */
    public static String uuid() {
        int machineId = 1;  //最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        return machineId + String.format("%011d", hashCodeV);
    }

    public static void main(String[] args) {
        System.out.println(uuid());
    }
}
