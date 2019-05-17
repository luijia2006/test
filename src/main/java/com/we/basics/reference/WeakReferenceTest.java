package com.we.basics.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;

/**
 * 弱引用与软引用的区别在于：
 * 1只具有弱引用的对象拥有更短暂的生命周期。
 * 在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，
 * 不管当前内存空间足够与否，都会回收它的内存。
 * 不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
 *
 * 2.同样，弱引用可以和一个引用队列(ReferenceQueue)联合使用，如果弱引用所引用的对象被垃圾回收，
 * Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。
 */
public class WeakReferenceTest {
    // 弱引用队列
    private final static ReferenceQueue<GCTarget> REFERENCE_QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        LinkedList<GCTargetWeakReference> gcTargetList = new LinkedList<>();

        // 创建弱引用的对象，依次加入链表中
        for (int i = 0; i < 5; i++) {
            GCTarget gcTarget = new GCTarget(String.valueOf(i));
            GCTargetWeakReference weakReference = new GCTargetWeakReference(gcTarget,
                REFERENCE_QUEUE);
            gcTargetList.add(weakReference);

            System.out.println("Just created GCTargetWeakReference obj: " +
                gcTargetList.getLast());
        }

        // 通知GC进行垃圾回收
        System.gc();

        try {
            // 休息几分钟，等待上面的垃圾回收线程运行完成
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 检查关联的引用队列是否为空
        Reference<? extends GCTarget> reference;
        while((reference = REFERENCE_QUEUE.poll()) != null) {
            if(reference instanceof GCTargetWeakReference) {
                System.out.println("In queue, id is: " +
                    ((GCTargetWeakReference) (reference)).id);
            }
        }
    }
}