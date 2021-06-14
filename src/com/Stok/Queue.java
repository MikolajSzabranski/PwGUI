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
    private final Condition ac = lock.newCondition();
    //private static int id = 0 ;
    public Queue(int nr, int numOfPpl, int capacity) {
        this.nr = nr;
        this.numOfPpl = numOfPpl;
        this.capacity = capacity;
    }

    public void run() throws InterruptedException {
        /*while(true){
            lock.lock();
            try {
                if (onWay == 1){
                    ac.await();
                }else{
                    ac.signal();
                }
            } finally {
                lock.unlock();
            }
        }*/

    }
}
