package com.udacity.gradle.builditbigger.jokesource;

import java.util.Random;

public class JokeSource {

    private String[] mJokes = new String[]{
            "Joke 1. Ha! ha! ha!ha !",
            "Joke 2. Ha! ha! ha!ha !",
            "Joke 3. Ha! ha! ha!ha !",
            "Joke 4. Ha! ha! ha!ha !",
            "Joke 5. Ha! ha! ha!ha !"
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
