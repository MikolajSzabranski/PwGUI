package com.Stok;

public class UpDown extends Thread {
    int id;
    Queue qu;
    int travelTime;
    int capacity;
    int serviceTime;
    int holdOn;

    private static int current = 0;
    private static int toService = 5; //liczba przejazdów do serwisowania

    public UpDown(int id, Queue qu, int travelTime, int serviceTime, int holdOn, int capacity) {
        this.id = id;
        this.qu = qu;
        this.holdOn = holdOn;
        this.serviceTime = serviceTime;
        this.travelTime = travelTime;
        //this.capacity = qu.capacity;
        this.capacity = capacity;
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
            try {
                Thread.sleep(serviceTime);
            } catch (InterruptedException e) {
            }
        }
    }
}
