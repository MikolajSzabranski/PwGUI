package com.sample;

import com.Stok.Queue;
import com.Stok.Ropeway;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


public class Controller implements Initializable {
    Thread[] UDM = new Thread[3];
    Queue[] que = new Queue[3];
    AtomicInteger pplInQueue[] = {  //chętni do wjechania poszczególnymi kolejkami
            new AtomicInteger(15),
            new AtomicInteger(19),
            new AtomicInteger(16)
    };
    int capacity[] = {0,0,0};//ładowności kolejek
    int travelTime[] = {0,0,0};//czas kursu wyciągu
    int serviceTime[] = {0,0,0};//czas serwisowania wyciągu
    int holdOn[] = {0,0,0};//czas oczekiwania po przyjezdzie
    int toService = 1; //liczba przejazdów do serwisowania
    int numOfObj = 3; //liczba wyciągów

    @FXML
    private ProgressBar progressBar1;
    @FXML
    private ProgressBar progressBar2;
    @FXML
    private ProgressBar progressBar3;
    @FXML
    private Label label1 = new Label("" + pplInQueue[0]);
    @FXML
    private Label label2 = new Label("" + pplInQueue[1]);
    @FXML
    private Label label3 = new Label("" + pplInQueue[2]);
    @FXML
    private Label label1Move;
    @FXML
    private Label label2Move;
    @FXML
    private Label label3Move;

    private void fromFile() throws FileNotFoundException {
        File file = new File("./src/dane.txt");
        Scanner scanner = new Scanner(file);
        for (int i = 0; i < 3; i++) {
            capacity[i] = scanner.nextInt();
        }
        for (int i = 0; i < 3; i++) {
            travelTime[i] = scanner.nextInt();
        }
        for (int i = 0; i < 3; i++) {
            serviceTime[i] = scanner.nextInt();
        }
        for (int i = 0; i < 3; i++) {
            holdOn[i] = scanner.nextInt();
        }
        toService = scanner.nextInt();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progressBar1.setProgress(0.0);
        progressBar2.setProgress(0.0);
        progressBar3.setProgress(0.0);

        try {
            fromFile();
        } catch (FileNotFoundException e) {}

        Platform.runLater(() -> {
            que[0] = new Queue(0, pplInQueue[0], capacity[0], label1, label1Move);
            que[1] = new Queue(1, pplInQueue[1], capacity[1], label2, label2Move);
            que[2] = new Queue(2, pplInQueue[2], capacity[2], label3, label3Move);

            UDM[0] = new Ropeway(que[0], travelTime[0], serviceTime[0], holdOn[0], toService, progressBar1, label1, label1Move);
            UDM[1] = new Ropeway(que[1], travelTime[1], serviceTime[1], holdOn[1], toService, progressBar2, label2, label2Move);
            UDM[2] = new Ropeway(que[2], travelTime[2], serviceTime[2], holdOn[2], toService, progressBar3, label3, label3Move);
            Arrays.stream(UDM).forEach(ropeway -> ropeway.setDaemon(true));

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
}
