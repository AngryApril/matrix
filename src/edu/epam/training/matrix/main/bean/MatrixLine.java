package edu.epam.training.matrix.main.bean;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by alexey.valiev on 6/6/19.
 */
public class MatrixLine {

    private static final Logger logger = Logger.getLogger(MatrixLine.class);

    private State state = State.UNCHANGED;

    private ReadWriteLock rwLock  = new ReentrantReadWriteLock();
    private int lineNumber;
    private ArrayList<Integer> integers = new ArrayList<>();

    public MatrixLine(int lineNumber, ArrayList<Integer> integers) {
        this.lineNumber = lineNumber;
        this.integers = integers;
    }


    public void set(int index, int element) {

        logger.info("Thread " + element + " Arrived to Line # " + index );

        Lock lock = rwLock.writeLock();

        if (state.equals(State.UNCHANGED)) {

            lock.lock();
            setState(State.LOCKED);
            integers.set(index, element);
            setState(State.CHANGED);

        } else return;

        logger.info(getState().toString() + " Line # " + index + " with Thread " + element);
        lock.unlock();
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }

    }

    public ArrayList<Integer> getIntegers() {
        return integers;
    }

    public void setIntegers(ArrayList<Integer> integers) {
        this.integers = integers;
    }

    public State getState() {
        return state;
    }

    private void setState(State state) {
        this.state = state;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {

        return integers.toString();

    }

    public enum State{
        UNCHANGED,
        LOCKED,
        CHANGED
    }

}
