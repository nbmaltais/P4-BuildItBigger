package com.udacity.gradle.builditbigger.jokeui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeFragment extends Fragment {

    static final String TAG=JokeFragment.class.getSimpleName();
    private static final String KEY_JOKE = "com.udacity.gradle.builditbigger.jokeui.JOKE";

    private TextView mJokeView;

    static public void fillIntent(Intent intent, Joke joke)
    {
        intent.putExtra(KEY_JOKE,joke);
    }

    public JokeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_joke, container, false);
        mJokeView = (TextView)root.findViewById(R.id.jokeView);

        Bundle args= getArguments();
        if(args!=null)
        {
            Joke joke = args.getParcelable(KEY_JOKE);
            setJoke(joke);
        }

        return root;
    }

    public void setJoke(Joke joke)
    {
        if(joke==null)
            return;

        mJokeView.setText(joke.getText());
    }

    public void setJokeFromIntent(Intent intent) {
        Joke joke = intent.getParcelableExtra(KEY_JOKE);
        setJoke(joke);
    }
}
