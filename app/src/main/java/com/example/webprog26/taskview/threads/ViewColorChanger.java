package com.example.webprog26.taskview.threads;

import android.graphics.Color;

import com.example.webprog26.taskview.interfaces.OnColorChangeCallback;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by webprog26 on 07.12.2016.
 */

public class ViewColorChanger extends Thread {

    OnColorChangeCallback mCallback;

    private static final long CHANGE_COLOR_INTERVAL_IN_SECONDS = 2;
    private int[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW};

    private Random mRandom;
    private volatile boolean mShouldStop = false;

    public ViewColorChanger(OnColorChangeCallback mCallback) {
        this.mCallback = mCallback;
        mRandom = new Random();
    }

    @Override
    public void run() {
        super.run();
        while (!mShouldStop){
            try {
                TimeUnit.SECONDS.sleep(CHANGE_COLOR_INTERVAL_IN_SECONDS);
                mCallback.onColorChange(colors[mRandom.nextInt(colors.length)]);
            } catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }

    public boolean isShouldStop() {
        return mShouldStop;
    }

    public void setShouldStop(boolean shouldStop) {
        this.mShouldStop = shouldStop;
    }
}
