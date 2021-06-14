package com.Stok;

public class UpDown extends Thread {
    private Queue qu;
    private int id;
    private int travelTime;
    private int capacity;
    private int serviceTime;
    private int holdOn;
    private int toService;

    private static int current = 0;

    public UpDown(Queue qu, int id, int travelTime, int serviceTime, int holdOn, int toService) {
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
                try {
                    qu.run();
                } catch (InterruptedException e) {
                }
                //wsiadanie
                if (qu.numOfPpl > capacity) {
                    current = capacity;
                    qu.numOfPpl -= capacity;
                } else {
                    current = qu.numOfPpl;
                    qu.numOfPpl = 0;
                }
                //czekanie na odjazd
                try {
                    Thread.sleep(holdOn);
                } catch (InterruptedException x) {
                }
                System.out.println("Kolejka " + (id + 1) + "\nOgień na tłoki " + current);
                qu.onWay = 1;
                //jazda w górę
                try {
                    Thread.sleep(travelTime);
                } catch (InterruptedException x) {
                }
                current = 0;
                //odczekanie i powrót
                try {
                    Thread.sleep(holdOn + travelTime);
                } catch (InterruptedException x) {
                }
                qu.onWay = 0;
            }
            //SERWIS
            System.out.println("SERWIS");
            try {
                Thread.sleep(serviceTime);
            } catch (InterruptedException e) {
            }
        }
    }
}
