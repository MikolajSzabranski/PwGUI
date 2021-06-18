package com.Stok;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ropeway extends Thread {
    private final Queue qu;
    private final int id;
    private final int travelTime;
    private final int capacity;
    private final int serviceTime;
    private final int holdOn;
    private final int toService;

    private final Lock lock = new ReentrantLock();
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
        while (true) {
            for (int i = 0; i < toService; i++) {
                lock.lock();
                try {
                    //qu.run();
                    //wsiadanie
                    int current;
                    if (qu.numOfPpl > capacity) {
                        current = capacity;
                        qu.numOfPpl -= current;
                    } else {
                        current = qu.numOfPpl;
                        qu.numOfPpl = 0;
                    }
                    //czekanie na odjazd
                    Thread.sleep(holdOn);
                    //jazda w górę
                    qu.onWay = 1;
                    //System.out.println("Kolejka " + (id + 1) + "\nOgień na tłoki " + current);
                    Thread.sleep(travelTime);
                    //odczekanie i powrót
                    Thread.sleep(holdOn + travelTime);
                } catch (InterruptedException e) {
                } finally {
                    qu.onWay = 0;
                    lock.unlock();
                }
            }
            //SERWIS
            //System.out.println("SERWIS "+(id+1));
            try {
                Thread.sleep(serviceTime);
            } catch (InterruptedException e) {}
        }
    }
}
