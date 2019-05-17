package com.we.basics.reference;

import java.lang.ref.*;

/**
 * 强引用、弱引用、软引用和虚引用
 * java执行GC判断对象是否存活有两种方式其中一种是引用计数。
 *
 * 在JDK 1.2以前的版本中，若一个对象不被任何变量引用，那么程序就无法再使用这个对象。
 * 也就是说，只有对象处于(reachable)可达状态，程序才能使用它。
 *
 * 从JDK 1.2版本开始，对象的引用被划分为4种级别，从而使程序能更加灵活地控制对象的生命周期。
 * 这4种级别由高到低依次为：强引用、软引用、弱引用和虚引用。
 */
public class ReferenceTest {
    /**
     * 强引用是使用最普遍的引用。如果一个对象具有强引用，那垃圾回收器绝不会回收它
     * 1.当内存空间不足时，Java虚拟机宁愿抛出OutOfMemoryError错误，使程序异常终止，也不会靠随意回收具有强引用的对象来解决内存不足的问题。
     *如果强引用对象不使用时，需要弱化从而使GC能够回收，如下：
     *strongReference = null;
     *显式地设置strongReference对象为null，或让其超出对象的生命周期范围，则gc认为该对象不存在引用，这时就可以回收这个对象。
     *具体什么时候收集这要取决于GC算法。
     *2.在一个方法的内部有一个强引用，这个引用保存在Java栈中，而真正的引用内容(Object)保存在Java堆中。
     *当这个方法运行完成后，就会退出方法栈，则引用对象的引用数为0，这个对象会被回收。
     *但是如果这个strongReference是全局变量时，就需要在不用这个对象时赋值为null，因为强引用不会被垃圾回收。
     *3.ArrayList的Clear方法：
     *在ArrayList类中定义了一个elementData数组，在调用clear方法清空数组时，每个数组元素被赋值为null。
     *不同于elementData=null，强引用仍然存在，避免在后续调用add()等方法添加元素时进行内存的重新分配。
     *使用如clear()方法内存数组中存放的引用类型进行内存释放特别适用，这样就可以及时释放内存。
     */
    public static void strongReference() {
        Object strongReference = new Object();
        // 省略其他操作
    }

    public static void softReference() {
        // 强引用
        // strongReference = new String("abc");
        // 软引用
        //String str = new String("abc");
        //SoftReference<String> softReference = new SoftReference<String>(str);

        //软引用可以和一个引用队列(ReferenceQueue)联合使用。
        // 如果软引用所引用对象被垃圾回收，JAVA虚拟机就会把这个软引用加入到与之关联的引用队列中。
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        String str = new String("abc");
        SoftReference<String> softReference1 = new SoftReference<>(str, referenceQueue);
        str = null;
        // Notify GC
        System.gc();
        System.out.println(softReference1.get()); // abc
        Reference<? extends String> reference = referenceQueue.poll();
        System.out.println(reference); //null
        //注意：软引用对象是在jvm内存不够的时候才会被回收，我们调用System.gc()方法只是起通知作用，JVM什么时候扫描回收对象是JVM自己的状态决定的。
        // 就算扫描到软引用对象也不一定会回收它，只有内存不够的时候才会回收。
        //当内存不足时，JVM首先将软引用中的对象引用置为null，然后通知垃圾回收器进行回收：
        //if(JVM内存不足) {
            // 将软引用中的对象引用置为null
        //str = null;
            // 通知垃圾回收器进行回收
        //System.gc();
        // }
    }


    /**
     * 弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。
     * 在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。
     * 不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
     */
    public static void weakReference() {
        String str = new String("abc");
        WeakReference<String> weakReference = new WeakReference<>(str);
        // 弱引用转强引用
        String strongReference = weakReference.get();
    }

    /**
     * 1.虚引用顾名思义，就是形同虚设。与其他几种引用都不同，虚引用并不会决定对象的生命周期。
     * 如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收。
     * 2.应用场景：
     * 虚引用主要用来跟踪对象被垃圾回收器回收的活动。
     * 3.虚引用与软引用和弱引用的一个区别在于：
     * 虚引用必须和引用队列(ReferenceQueue)联合使用。当垃圾回收器准备回收一个对象时，
     * 如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之关联的引用队列中。
     * 4.程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要进行垃圾回收。
     * 如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动。
     */
    public static void phantomeference() {
        String str = new String("abc");
        ReferenceQueue queue = new ReferenceQueue();
        // 创建虚引用，要求必须与一个引用队列关联
        PhantomReference pr = new PhantomReference(str, queue);
    }


    public static void main(String[] args) {
        //Java中4种引用的级别和强度由高到低依次为：强引用 -> 软引用 -> 弱引用 -> 虚引用
        softReference();
    }
}
