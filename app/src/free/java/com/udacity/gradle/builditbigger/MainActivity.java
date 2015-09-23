package com.udacity.gradle.builditbigger;

import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.jokeui.Joke;
import com.udacity.gradle.builditbigger.jokeui.JokeActivity;


public class MainActivity extends MainActivityBase {

    static final String TEST_DEVICE_ID = "6D50F110DA53465EFAB0C36D381C8A13";
    static final String TAG = MainActivity.class.getSimpleName();
    private InterstitialAd mInterstitialAd;
    private Joke mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(TEST_DEVICE_ID)
                .build();
        mAdView.loadAd(adRequest);

        // Code for interstitial ad is taken from https://developers.google.com/admob/android/interstitial
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                // Show the joke
                JokeActivity.start(MainActivity.this,mJoke);
            }
        });

        requestNewInterstitial();
    }


    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(TEST_DEVICE_ID)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    protected void showJoke(String jokeText) {

        if(mInterstitialAd.isLoaded())
        {
            // Store the joke, we will show it after the ad is closed
            mJoke = new Joke(jokeText);
            mInterstitialAd.show();
        }
        else
        {
            super.showJoke(jokeText);
        }
    }
}
