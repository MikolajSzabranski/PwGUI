package com.Stok;

public class Test {
    public static int pplInQueue[] = {5, 9, 6};//chętni do wjechania poszczególnymi kolejkami
    public static int capacity[] = {3, 4, 2};//ładowności kolejek
    public static int travelTime[] = {1000, 400, 600};//czas kursu wyciągu
    public static int serviceTime[] = {10000, 12000, 11000};//czas serwisowania wyciągu
    public static int holdOn[] = {300, 200, 200};//czas oczekiwania po przyjezdzie
    public static int toService = 5; //liczba przejazdów do serwisowania
    private static int numOfObj = 3; //liczba wyciągów

    public Test() {
    }

    public static void run() throws InterruptedException {
        Thread[] UDM = new Thread[3];

        Queue[] que = new Queue[3];
        for (int i = 0; i < numOfObj; i++) {
            que[i] = new Queue(i, pplInQueue[i], capacity[i]);
        }

        for (int i = 0; i < numOfObj; i++) {
            UDM[i] = new Ropeway(que[i], i, travelTime[i], serviceTime[i], holdOn[i], toService);
        }

        for (int i = 0; i < numOfObj; i++) {
            UDM[i].start();
        }

        for (int i = 0; i < numOfObj; i++) {
            UDM[i].join();
        }
    }

}
