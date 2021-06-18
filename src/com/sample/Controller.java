package com.sample;

import com.Stok.Queue;
import com.Stok.Ropeway;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable{
    @FXML
    private ProgressBar progressBar1;
    int pplInQueue[] = {5, 9, 6};//chętni do wjechania poszczególnymi kolejkami
    int capacity[] = {3, 4, 2};//ładowności kolejek
    int travelTime[] = {1000, 400, 600};//czas kursu wyciągu
    int serviceTime[] = {10000, 12000, 11000};//czas serwisowania wyciągu
    int holdOn[] = {300, 200, 200};//czas oczekiwania po przyjezdzie
    int toService = 5; //liczba przejazdów do serwisowania
    int numOfObj = 3; //liczba wyciągów
    class bg_Thread implements Runnable{
        @Override
        public void run(){
            for(int i = 0; i<100; i++){
                try{
                    progressBar1.setProgress(i/100.0);
                    //progressBar2.setProgress(i/100.0);
                    //progressBar3.setProgress(i/100.0);
                    Thread.sleep(100);
                } catch (InterruptedException ie) {}
            }
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event){
        Thread th = new Thread(new bg_Thread());
        th.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        progressBar1.setProgress(0.0);

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
            try {
                UDM[i].join();
            } catch (InterruptedException ie) {}
        }
    }

    public void bazaSczyt(ActionEvent e){
        pplInQueue[0]++;
    }
    public void bazaSrodek(ActionEvent e){
        pplInQueue[1]++;
    }
    public void srodekSczyt(ActionEvent e){
        pplInQueue[2]++;
    }
    public void zamknijProgram(ActionEvent e){
        //Main.stage.close();
    }
    public void BaSz(){

    }
}
