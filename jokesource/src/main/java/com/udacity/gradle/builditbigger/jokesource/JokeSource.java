package com.udacity.gradle.builditbigger.jokesource;

import java.util.Random;

public class JokeSource {

    private String[] mJokes = new String[]{
            "Joke 1. Hahahaha",
            "Joke 2. HAHAHAHAHA",
            "Joke 3. HAHAHAHAHA",
            "Joke 4. HAHAHAHAHA",
            "Joke 5. HAHAHAHAHA"
            };

    public JokeSource() {
    }

    public String tellJoke()
    {
        double i = Math.random() * mJokes.length;
        if(i<0) i=-i;
        int idx = (int)i % mJokes.length;
        return mJokes[idx];
    }
}
