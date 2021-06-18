package com.Stok;

import javafx.scene.control.Label;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {
    static int numOfPpl;
    int capacity;
    int nr;
    public int onWay = 0;
    Label lab;

    private final Lock lock = new ReentrantLock();
    private final Condition ac = lock.newCondition();

    public Queue(int nr, int numOfPpl, int capacity, Label lab) {
        this.nr = nr;
        this.numOfPpl = numOfPpl;
        this.capacity = capacity;
        this.lab=lab;
    }

    public void update(){
        lab.setText(Integer.toString(numOfPpl));
    }

    public void add(){
        numOfPpl++;
        update();
    }
}
