package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;
import com.udacity.gradle.builditbigger.backend.jokeApi.model.JokeBean;

import java.io.IOException;

/**
 * Created by Nicolas on 2015-09-22.
 */
abstract class JokeEndpointsAsyncTask extends AsyncTask<Void, Void, JokeBean> {
    private JokeApi mJokeService = null;
    private String mRootUrl;

    public JokeEndpointsAsyncTask(String rootUrl) {
        mRootUrl = rootUrl;
    }

    @Override
    protected JokeBean doInBackground(Void... args) {
        if (mJokeService == null) {  // Only do this once

            //String rootUrl = getString(R.string.joke_api_url);
            Log.d(MainActivity.TAG, "Creating joke service at " + mRootUrl);

            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(mRootUrl)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            mJokeService = builder.build();
        }


        try {
            Log.i(MainActivity.TAG, "Fetching joke.");
            JokeBean joke = mJokeService.getJoke(0).execute();
            return joke;
        } catch (IOException e) {
            Log.e(MainActivity.TAG, "Joke Api error", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(JokeBean result) {
        if (result != null) {
            onJokeLoaded(result);
        } else {
            onError();
        }
    }

    abstract void onJokeLoaded(JokeBean joke);

    abstract void onError();
}
