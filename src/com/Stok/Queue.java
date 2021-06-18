package com.Stok;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {
    AtomicInteger numOfPpl;
    int capacity;
    int current;
    int nr;
    Label lab;
    Label labMove;

    private final Lock lock = new ReentrantLock();
    private final Condition ac = lock.newCondition();

    public Queue(int nr, AtomicInteger numOfPpl, int capacity, Label lab, Label labMove) {
        this.nr = nr;
        this.numOfPpl = numOfPpl;
        this.capacity = capacity;
        this.lab = lab;
        this.labMove = labMove;
    }

    public void update() {
        Platform.runLater(()->{
            lab.setText(Integer.toString(numOfPpl.get()));
            labMove.setText(Integer.toString(current));
        });
    }

    public synchronized void add() {
        numOfPpl.incrementAndGet();
        update();
    }

    public synchronized void subNumOfPpl(int sub) {
        if (numOfPpl.get() > sub) {
            current = sub;
            numOfPpl.set(numOfPpl.get() - capacity);
        } else {
            current = numOfPpl.get();
            numOfPpl.set(0);
        }
        update();
    }
}
