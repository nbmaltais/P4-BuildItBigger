package com.udacity.gradle.builditbigger.jokeui;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nicolas on 2015-09-21.
 */
public class Joke implements Parcelable {
    private final String mText;

    public Joke(String text)
    {
        mText =text;
    }

    public String getText() {
        return mText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mText);
    }

    protected Joke(Parcel in) {
        this.mText = in.readString();
    }

    public static final Parcelable.Creator<Joke> CREATOR = new Parcelable.Creator<Joke>() {
        public Joke createFromParcel(Parcel source) {
            return new Joke(source);
        }

        public Joke[] newArray(int size) {
            return new Joke[size];
        }
    };
}
