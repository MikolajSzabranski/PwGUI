package com.Stok;

public class Test {
    public static int num[] = {5, 4, 6};//chętni do wjechania poszczególnymi kolejkami
    public static int travelTime[] = {1000, 400, 600};//czas kursu wyciągu
    public static int serviceTime[] = {10000, 12000, 11000};//czas serwisowania wyciągu
    public static int holdOn[] = {300, 200, 200};//czas oczekiwania po przyjezdzie
    public static int capacity[] = {2, 3, 2};//ładowności kolejek

    public Test() {
    }

    public static void run() throws InterruptedException {
        Thread[] UDM = new Thread[3];

        Queue[] que = new Queue[3];
        for (int i = 0; i < 3; i++) {
            que[i] = new Queue(i, num[i], capacity[i]);
        }

        for (int i = 0; i < 3; i++) {
            UDM[i] = new UpDown(i, que[i], travelTime[i], serviceTime[i], holdOn[i]);
        }

        for (int i = 0; i < 3; i++) {
            UDM[i].start();
        }

        for (int i = 0; i < 3; i++) {
            UDM[i].join();
        }
    }

}
