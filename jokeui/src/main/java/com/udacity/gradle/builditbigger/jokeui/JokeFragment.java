package com.udacity.gradle.builditbigger.jokeui;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stylingandroid.tts.TextToSpeechCompat;
import com.udacity.gradle.builditbigger.jokeui.view.TypeWriter;

import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeFragment extends Fragment {

    static final String TAG=JokeFragment.class.getSimpleName();
    private static final String KEY_JOKE = "com.udacity.gradle.builditbigger.jokeui.JOKE";

    private TypeWriter mJokeView;
    private Joke mJoke;
    private TextToSpeechCompat mTts;
    private boolean mTtsAvailable = false;

    static public void fillIntent(Intent intent, Joke joke)
    {
        intent.putExtra(KEY_JOKE,joke);
    }

    public JokeFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mTts =  TextToSpeechCompat.newInstance(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS)
                {
                    mTtsAvailable=true;
                    mTts.setLanguage(Locale.ENGLISH);
                    if(mJoke!=null)
                        speakJoke();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_joke, container, false);
        mJokeView = (TypeWriter)root.findViewById(R.id.jokeView);
        mJokeView.setCharacterDelay(20);

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

        mJoke=joke;
        mJokeView.animateText(joke.getFullText());

        if(mTtsAvailable)
            speakJoke();
    }

    private void speakJoke() {
        if(mJoke==null || mTtsAvailable==false)
            return;

        mTts.speak(mJoke.getText(),TextToSpeech.QUEUE_ADD);
        if(mJoke.hasPunch()) {
            mTts.silence(1000, TextToSpeech.QUEUE_ADD);
            mTts.speak(mJoke.getPunch(), TextToSpeech.QUEUE_ADD);
        }
    }

    public void setJokeFromIntent(Intent intent) {
        Joke joke = intent.getParcelableExtra(KEY_JOKE);
        setJoke(joke);
    }

    @Override
    public void onDestroy() {
        mTts.shutdown();
        super.onDestroy();
    }
}
