package com.lizl.demo.myhandler;

/**
 * @author Lizl
 * @date 2017/8/4.
 */

public class Message {
    Handler target;
    public int what;
    public Object obj;

    @Override
    public String toString() {
        return obj.toString();
    }
}
