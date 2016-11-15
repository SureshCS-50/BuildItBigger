package com.sureshcs50.builditbigger.app.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.sureshcs50.builditbigger.EndpointsAsyncTask;
import com.sureshcs50.builditbigger.OnJokeReceivedListener;
import com.sureshcs50.builditbigger.R;
import com.sureshcs50.jokelib_android.JokeActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements OnJokeReceivedListener {

    private ProgressBar mProgressBar;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button) root.findViewById(R.id.btnJoke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startJokeActivity();
            }
        });

        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        return root;
    }

    @Override
    public void onReceived(String joke) {
        mProgressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(getActivity(), JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.INTENT_KEY, joke);
        startActivity(intent);
    }

    public void startJokeActivity(){
        mProgressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(this);
    }
}
