package com.lizl.demo.myhandler;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lizl
 * @date 2017/8/4.
 */
public class MessageQueue {
    Lock lock;
    Condition mEmptyQueue;
    Condition mFullQueue;
    Message[] mMessages;
    int putIndex;
    int takeIndex;
    int count;

    public MessageQueue() {
        mMessages = new Message[50];
        lock = new ReentrantLock();
        mEmptyQueue = lock.newCondition();
        mFullQueue = lock.newCondition();
    }

    final void enQueueMessage(Message msg) {
        try {
            lock.lock();
            while (count == mMessages.length) {
                try {
                    mFullQueue.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mMessages[putIndex] = msg;
            putIndex = (++putIndex == mMessages.length ? 0 : putIndex);
            count++;
            mEmptyQueue.signalAll();
        } finally {
            lock.unlock();
        }
    }

    final Message next(){
        Message msg = null;
        try {
            lock.lock();
            while (count==0){
                try {
                    mEmptyQueue.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            msg = mMessages[takeIndex];
            mMessages[takeIndex]=null;
            takeIndex = (++takeIndex==mMessages.length?0:takeIndex);
            count--;
            mFullQueue.signalAll();
        }finally {
            lock.unlock();
        }
        return msg;
    }

}
