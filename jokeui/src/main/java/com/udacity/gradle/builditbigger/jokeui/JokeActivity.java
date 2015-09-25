package com.udacity.gradle.builditbigger.jokeui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class JokeActivity extends AppCompatActivity {

    static final String TAG=JokeActivity.class.getSimpleName();

    /**
     * Starts the actvity to display the specified joke
     * @param context
     * @param joke The joke to display
     */
    public static void start(Context context, Joke joke) {
        Intent starter = new Intent(context, JokeActivity.class);
        JokeFragment.fillIntent(starter,joke);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        JokeFragment fragment = (JokeFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment.setJokeFromIntent(getIntent(), savedInstanceState==null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_joke, menu);
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
}
