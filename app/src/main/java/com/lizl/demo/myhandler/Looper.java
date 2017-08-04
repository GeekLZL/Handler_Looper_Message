package com.lizl.demo.myhandler;

/**
 * @author Lizl
 * @date 2017/8/4.
 */

public class Looper {
    static ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    MessageQueue mQueue;

    public Looper() {
        mQueue = new MessageQueue();
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("只能创建一个Looper对象");
        }
        sThreadLocal.set(new Looper());

    }

    public static void loop() {
        Looper me = myLooper();
        if (me == null) {
            throw new RuntimeException("未调用Looper.prepare()");
        }
        MessageQueue queue = me.mQueue;
        for(;;){
           Message msg =  queue.next();
            if (msg==null||msg.target ==null)
                continue;
            msg.target.dispatchMessage(msg);
        }
    }
}
