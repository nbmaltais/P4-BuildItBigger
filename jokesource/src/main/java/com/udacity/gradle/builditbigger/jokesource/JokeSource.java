package com.udacity.gradle.builditbigger.jokesource;

import java.util.Random;

public class JokeSource {

    private String[] mJokes = new String[]{
            // This one is from from https://funnyjokesandlaughs.wordpress.com/category/open-source/
            "If at first you don't succeed at installing your OS, try, try again. If it keeps failing on you, ask yourself why you bought that Microsoft product.",
            // Those are from http://academictips.org/funny-jokes/42-funny-one-liners/
            "Why do men find it difficult to make eye contact? Breasts don't have eyes.",
            "How do you get a sweet 80-year-old lady to say the F word? Get another sweet little 80-year-old lady to yell BINGO!",
            "What did one ocean say to the other ocean? Nothing, they just waved.",
            "A day without sunshine is like, night.",
            "For Sale: Parachute. Only used once, never opened.",
            "A bank is a place that will lend you money, if you can prove that you don't need it.",
            "What is faster Hot or cold? Hot, because you can catch a cold.",
            "What's the difference between a new husband and a new dog? After a year, the dog is still excited to see you.",
            "Why is it so hard for women to find men that are sensitive, caring, and good-looking? Because those men already have boyfriends."

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
