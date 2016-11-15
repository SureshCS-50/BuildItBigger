package com.sureshcs50.builditbigger.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
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

    private InterstitialAd mInterstitialAd;
    private ProgressBar mProgressBar;
    private String mJoke;
    private Button mBtnJoke;


    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startJokeActivity();
            }
        });

        requestNewInterstitial();
        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        mBtnJoke = (Button) root.findViewById(R.id.btnJoke);
        mBtnJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchJoke();
            }
        });

        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onReceived(String joke) {
        mProgressBar.setVisibility(View.INVISIBLE);
        mBtnJoke.setEnabled(true);
        mJoke = joke;
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        startJokeActivity();
    }

    private void startJokeActivity() {
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.KEY_JOKE, mJoke);
        startActivity(intent);
    }

    public void fetchJoke() {
        mProgressBar.setVisibility(View.VISIBLE);
        mBtnJoke.setEnabled(false);
        new EndpointsAsyncTask().execute(this);
    }
}

