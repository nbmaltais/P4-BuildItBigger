package com.udacity.gradle.builditbigger.utils;

/**
 * Class inspired by this post
 * http://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework
 */
public class Synchronizer {

    public void doWait(long l){
        synchronized(this){
            try {
                this.wait(l);
            } catch(InterruptedException e) {
            }
        }
    }



    public void doNotify() {
        synchronized(this) {
            this.notify();
        }
    }


    public void doWait() {
        synchronized(this){
            try {
                this.wait();
            } catch(InterruptedException e) {
            }
        }
    }
}