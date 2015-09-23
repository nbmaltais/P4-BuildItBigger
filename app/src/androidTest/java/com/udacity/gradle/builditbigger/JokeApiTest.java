package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import com.udacity.gradle.builditbigger.backend.jokeApi.model.JokeBean;
import com.udacity.gradle.builditbigger.utils.Synchronizer;

/**
 * Created by Nicolas on 2015-09-22.
 */
public class JokeApiTest extends AndroidTestCase {

    JokeBean mResult=null;
    boolean mFailed=false;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mFailed=false;
        mResult=null;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    void setResult(JokeBean r)
    {
        mResult=r;
    }
    void setFailed(boolean f)
    {
        mFailed=f;
    }

    public void testAsyncTask()
    {
        final Synchronizer sync = new Synchronizer();
        String rootUrl = "http://192.168.1.30:8080/_ah/api/";


        JokeEndpointsAsyncTask task = new JokeEndpointsAsyncTask(rootUrl) {
            @Override
            void onJokeLoaded(JokeBean joke) {
                setResult(joke);
                sync.doNotify();
            }

            @Override
            void onError() {
                setFailed(true);
                sync.doNotify();;
            }
        };

        task.execute();
        sync.doWait();

        assertFalse(mFailed);
        assertFalse(mResult.getData().isEmpty());

    }
}
