package com.Stok;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {
    int numOfPpl;
    int capacity;
    int nr;
    public int onWay = 0;

    private final Lock lock = new ReentrantLock();
    //private final Condition ac = lock.newCondition();
    //private static int id = 0 ;
    public Queue(int nr, int numOfPpl, int capacity) {
        this.nr = nr;
        this.numOfPpl = numOfPpl;
        this.capacity = capacity;
    }

    public void run() throws InterruptedException {
        lock.lock();
        try {
/*            if (numOfPpl > capacity){
                current = capacity;
                numOfPpl -= capacity;
            }else{
                current=numOfPpl;
                numOfPpl=0;
            }*/
        } finally {
            lock.unlock();
        }
    }
}
