package com.sureshcs50.jokelib_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public final static String KEY_JOKE = "KEY_JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke = getIntent().getStringExtra(KEY_JOKE);
        TextView textViewJoke = (TextView) findViewById(R.id.txtJoke);
        textViewJoke.setText(joke);
    }
}
