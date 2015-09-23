package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.backend.jokeApi.model.JokeBean;
import com.udacity.gradle.builditbigger.jokeui.Joke;
import com.udacity.gradle.builditbigger.jokeui.JokeActivity;


public class MainActivity extends AppCompatActivity {

    static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("6D50F110DA53465EFAB0C36D381C8A13")
                .build();
        mAdView.loadAd(adRequest);
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

    public void tellJokeClicked(View view){
        String rootUrl = getString(R.string.joke_api_url);
        JokeEndpointsAsyncTask task = new JokeEndpointsAsyncTask(rootUrl) {

            @Override
            void onJokeLoaded(JokeBean joke) {
                showJoke(joke.getData());
            }

            @Override
            void onError() {
                Toast.makeText(MainActivity.this, "Joke API failed", Toast.LENGTH_SHORT).show();
            }
        };

        task.execute();
    }

    private void showJoke( String jokeText )
    {
        JokeActivity.start(this, new Joke(jokeText));
    }


}
