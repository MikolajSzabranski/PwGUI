package com.sample;

import com.Stok.Queue;
import com.Stok.Ropeway;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    Thread[] UDM = new Thread[3];
    Queue[] que = new Queue[3];
    int pplInQueue[] = {5, 9, 6};//chętni do wjechania poszczególnymi kolejkami
    int capacity[] = {3, 4, 2};//ładowności kolejek
    int travelTime[] = {3000, 2000, 1500};//czas kursu wyciągu
    int serviceTime[] = {10000, 12000, 11000};//czas serwisowania wyciągu
    int holdOn[] = {300, 200, 200};//czas oczekiwania po przyjezdzie
    int toService = 5; //liczba przejazdów do serwisowania
    int numOfObj = 3; //liczba wyciągów

    @FXML
    private ProgressBar progressBar1;
    @FXML
    private ProgressBar progressBar2;
    @FXML
    private ProgressBar progressBar3;
    @FXML
    private Label label1 = new Label(Integer.toString(pplInQueue[0]));
    @FXML
    private Label label2 = new Label(Integer.toString(pplInQueue[1]));
    @FXML
    private Label label3 = new Label(Integer.toString(pplInQueue[2]));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progressBar1.setProgress(0.0);
        progressBar2.setProgress(0.0);
        progressBar3.setProgress(0.0);

        Platform.runLater(() -> {
            que[0] = new Queue(0, pplInQueue[0], capacity[0], label1);
            que[1] = new Queue(1, pplInQueue[1], capacity[1], label2);
            que[2] = new Queue(2, pplInQueue[2], capacity[2], label3);

            UDM[0] = new Ropeway(que[0], 0, travelTime[0], serviceTime[0], holdOn[0], toService, progressBar1, label1);
            UDM[1] = new Ropeway(que[1], 1, travelTime[1], serviceTime[1], holdOn[1], toService, progressBar2, label2);
            UDM[2] = new Ropeway(que[2], 2, travelTime[2], serviceTime[2], holdOn[2], toService, progressBar3, label3);

            for (int i = 0; i < numOfObj; i++) {
                UDM[i].start();
            }
        });
    }

    public void bazaSczyt(ActionEvent e) {
        que[0].add();
    }

    public void bazaSrodek(ActionEvent e) {
        que[1].add();
    }

    public void srodekSczyt(ActionEvent e) {
        que[2].add();
    }

    public void BaSz() {

    }
}
