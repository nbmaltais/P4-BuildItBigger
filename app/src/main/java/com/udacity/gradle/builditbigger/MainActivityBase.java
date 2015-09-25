package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.backend.jokeApi.model.JokeBean;
import com.udacity.gradle.builditbigger.jokeui.Joke;
import com.udacity.gradle.builditbigger.jokeui.JokeActivity;
import com.udacity.gradle.builditbigger.jokeui.JokeFragment;

/**
 * Created by Nicolas on 2015-09-22.
 * Base class for main activity. Free version can overide methods to show ads.
 */
public class MainActivityBase extends AppCompatActivity {

    static final String TAG = MainActivity.class.getSimpleName();
    MainActivityFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragment = (MainActivityFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);

        // Prepare TTS
        JokeFragment.initTextToSpeech(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void showJoke( String jokeText )
    {
        JokeActivity.start(this, new Joke(jokeText));
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"onStop");
        super.onStop();
    }
}
