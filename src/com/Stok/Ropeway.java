package com.Stok;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
    ProgressBar progressBar;
    Label label;

    private final Lock lock = new ReentrantLock();

    public Ropeway(Queue qu, int id, int travelTime, int serviceTime, int holdOn, int toService, ProgressBar progressBar, Label label) {
        this.qu = qu;
        this.id = id;
        this.holdOn = holdOn;
        this.serviceTime = serviceTime;
        this.travelTime = travelTime;
        this.toService = toService;
        this.capacity = qu.capacity;
        this.progressBar = progressBar;
        this.label = label;
    }

    public void run() {
        while (true) {
            for (int i = 0; i < toService; i++) {
                lock.lock();
                try {
                    //wsiadanie
                    int current;
                    if (qu.numOfPpl > capacity) {
                        current = capacity;
                        qu.numOfPpl -= current;
                    } else {
                        current = qu.numOfPpl;
                        qu.numOfPpl = 0;
                    }
                    qu.update();
                    //czekanie na odjazd
                    Thread.sleep(holdOn);
                    //jazda w górę
                    for (int j = 0; j < 100; j++) {
                        try {
                            progressBar.setProgress(j / 100.0);
                            Thread.sleep(travelTime / 100);
                        } catch (InterruptedException ie) {
                        }
                    }
                    qu.onWay = 1;
                    if(id==0)System.out.println(""+qu.numOfPpl);
                    //odczekanie i powrót
                    Thread.sleep(holdOn + travelTime);
                    for (int j = 100; j > 0; j--) {
                        try {
                            progressBar.setProgress(j / 100.0);
                            Thread.sleep(travelTime / 100);
                        } catch (InterruptedException ie) {
                        }
                    }
                } catch (InterruptedException e) {
                } finally {
                    qu.onWay = 0;
                    lock.unlock();
                }
            }
            //SERWIS
            try {
                Thread.sleep(serviceTime);
            } catch (InterruptedException e) {
            }
        }
    }
}
