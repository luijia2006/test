package com.we.basics.algorithm;

import java.util.Random;

/**
 * https://mp.weixin.qq.com/s/PmgCc29GU7THagvbg-MZKw
 * @author vincent
 * @time 2019-08-07 11:59
 * 在内存中维护一个长度为N的数组，根据堆的性质，每一个节点都比他的左右子节点小，
 * 先取出前N个数并构建小顶堆，然后将所有数据与堆顶比较大小，如果比堆顶小就直接丢弃，
 * 如果比堆顶大则替换堆顶，并且重新构建这个堆。

 * 构建小顶堆的过程：先要找到最后一个非叶子节点，数组的长度为6，
 * 那么最后一个非叶子节点就是：长度/2-1，也就是6/2-1=2，然后下一步就是比较该节点值和它的左右节点值，
 * 如果该节点大于其左\右子树的值就交换（意思就是将最小的值放到该节点）。
 * 如果该节点不是叶子结点，则递归这一过程，直到这个节点变成叶子节点。
 */
public class TopN {
    public static int N = 6; //Top10
    public static int TOTOL = 10; //1亿个整数
    public static int total_arrs[] =  new int[TOTOL];
    public static int result[] = new int[N]; //在内存维护一个长度为N的小顶堆
    public static int resultLen = result.length;
    //堆中元素的有效元素 heapSize<=resultLen
    public static int heapSize = resultLen;
    public static void main(String[] args) {
        //生成随机数组
        for(int i = 0;i<TOTOL;i++){
            total_arrs[i] = new Random().nextInt(999999999);
        }

        //构建初始堆
        for(int i =  0;i<N;i++){
            result[i] = total_arrs[i];
        }
        //构建小顶堆
        long start =System.currentTimeMillis();
        buildMinHeap();
        for(int i = N;i<TOTOL;i++){
            if(total_arrs[i] > result[0]){
                result[0] = total_arrs[i];
                minHeap(0);
            }
        }
        System.out.println(TOTOL+"个数，求Top"+N+"，耗时"+(System.currentTimeMillis()-start)+"毫秒");
        print();
    }


    /**
     * 自底向上构建小堆
     */
    public static void buildMinHeap(){
        int size = resultLen / 2 -1 ; //最后一个非叶子节点
        for(int i = size;i>=0;i--){
            minHeap(i);
        }
    }

    /**
     * i节点为根及子树是一个小堆
     * @param i
     */
    public static void minHeap(int i){
        int l = left(i);
        int r = right(i);
        int index = i;
        if(l<heapSize && result[l]< result[index]){
            index = l;
        }
        if(r<heapSize && result[r]< result[index]){
            index = r;
        }
        if(index != i){
            int t = result[index];
            result[index] = result[i];
            result[i] = t;
            //递归向下构建堆
            minHeap(index);
        }
    }

    /**
     * 返回i节点的左孩子
     * @param i
     * @return
     */
    public static int left(int i){
        return 2*i;
    }

    /**
     * 返回i节点的右孩子
     * @param i
     * @return
     */
    public static int right(int i){
        return 2*i+1;
    }
    /**
     * 打印
     */
    public  static void print(){
        for(int a: result){
            System.out.print(a+",");
        }
        System.out.println();
    }
}