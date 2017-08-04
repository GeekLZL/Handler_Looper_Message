package com.lizl.demo.myhandler;

/**
 * @author Lizl
 * @date 2017/8/4.
 */

public class Handler {
    Looper mLooper;
    MessageQueue mQueue;

   public Handler(){
        mLooper = Looper.myLooper();
       if (mLooper==null){
           throw new RuntimeException("需要调用Looper.prepare()");
       }
       mQueue = mLooper.mQueue;
    }

    public final void sendMessage(Message msg){
        MessageQueue queue = this.mQueue;
        if (queue != null) {
            msg.target = this;
            queue.enQueueMessage(msg);
        }else {
            throw new RuntimeException("sendMessage no queue");
        }
    }

    public  void handMessage(Message msg){

    }

    public void dispatchMessage(Message msg){
        handMessage(msg);
    }
}
