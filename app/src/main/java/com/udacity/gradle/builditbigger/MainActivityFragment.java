package com.udacity.gradle.builditbigger;

import android.animation.Animator;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.backend.jokeApi.model.JokeBean;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Button mButton;
    ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mButton = (Button) root.findViewById(R.id.jokeButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJokeClicked();
            }
        });
        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        setLoading(false);
    }

    /**
     * Hide/show the loading indicator
     * @param loading
     */
    public void setLoading(boolean loading) {
        if(loading)
        {
            mButton.setVisibility(View.INVISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
        }
        else
        {
            mButton.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void tellJokeClicked(){
        String rootUrl = getString(R.string.joke_api_url);
        JokeEndpointsAsyncTask task = new JokeEndpointsAsyncTask(rootUrl) {

            @Override
            void onJokeLoaded(JokeBean joke) {
                setLoading(false);
                showJoke(joke.getData());
            }

            @Override
            void onError() {
                setLoading(false);
                Toast.makeText(getActivity(), "Joke API failed", Toast.LENGTH_SHORT).show();
            }
        };

        mButton.animate()
                .scaleX(0)
                .scaleY(0)
                .setInterpolator(new BounceInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        setLoading(true);
                        mButton.setScaleX(1);
                        mButton.setScaleY(1);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });


        task.execute();
    }

    private void showJoke(String data) {
        ((MainActivityBase)getActivity()).showJoke(data);
    }
}
