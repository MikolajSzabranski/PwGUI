package com.Stok;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ropeway extends Thread {
    private Queue qu;
    private int id;
    private int travelTime;
    private int capacity;
    private int serviceTime;
    private int holdOn;
    private int toService;
    private static int current = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition ac = lock.newCondition();

    public Ropeway(Queue qu, int id, int travelTime, int serviceTime, int holdOn, int toService) {
        this.qu = qu;
        this.id = id;
        this.holdOn = holdOn;
        this.serviceTime = serviceTime;
        this.travelTime = travelTime;
        this.toService = toService;
        this.capacity = qu.capacity;
    }

    public void run() {
       /* try {
            qu.run();
        } catch (InterruptedException e) {}*/
        while (true) {
            for (int i = 0; i < toService; i++) {
                lock.lock();
                try {
                    qu.run();
                    //wsiadanie
                    if (qu.numOfPpl > capacity) {
                        current = capacity;
                        qu.numOfPpl -= capacity;
                    } else {
                        current = qu.numOfPpl;
                        qu.numOfPpl = 0;
                    }
                    //czekanie na odjazd
                    Thread.sleep(holdOn);
                    //jazda w górę
                    qu.onWay = 1;
                        //ac.await();
                    System.out.println("Kolejka " + (id + 1) + "\nOgień na tłoki " + current);
                    Thread.sleep(travelTime);
                        //ac.signal();
                    current = 0;
                    //odczekanie i powrót
                    Thread.sleep(holdOn + travelTime);
                } catch (InterruptedException e) {
                } finally {
                    qu.onWay = 0;
                    lock.unlock();
                }
            }
            //SERWIS
            System.out.println("SERWIS");
            try {
                Thread.sleep(serviceTime);
            } catch (InterruptedException e) {}
        }
    }
}
