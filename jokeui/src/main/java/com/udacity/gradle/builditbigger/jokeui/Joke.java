package com.udacity.gradle.builditbigger.jokeui;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nicolas on 2015-09-21.
 */
public class Joke implements Parcelable {
    private final String mText;
    private String mPunch;

    public Joke(String text)
    {
        int i = text.indexOf('?');

        if(i!=-1) {
            mText = text.substring(0, i+1).trim();
            mPunch = text.substring(i + 1).trim();
        }
        else
        {
            mText=text;

        }
    }

    public String getText() {
        return mText;
    }
    public String getPunch() {
        return mPunch;
    }

    public String getFullText()
    {
        if(mPunch!=null)
            return mText + "\n\n" + mPunch;
        else
            return mText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mText);
        dest.writeString(this.mPunch);
    }

    protected Joke(Parcel in) {
        this.mText = in.readString();
        this.mPunch = in.readString();
    }

    public static final Creator<Joke> CREATOR = new Creator<Joke>() {
        public Joke createFromParcel(Parcel source) {
            return new Joke(source);
        }

        public Joke[] newArray(int size) {
            return new Joke[size];
        }
    };

    public boolean hasPunch() {
        return mPunch!=null && !mPunch.isEmpty();
    }
}
