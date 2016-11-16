package com.sureshcs50.builditbigger;

import android.os.ConditionVariable;
import android.test.AndroidTestCase;
import android.text.TextUtils;

/**
 * Created by Sureshkumar on 16/11/16.
 *
 * Reference : (Testing AsyncTask in Android)
 *
 * https://gist.github.com/he9lin/2195897
 */

public class ApplicationTest extends AndroidTestCase implements OnJokeReceivedListener {

    private EndpointsAsyncTask endpointsAsyncTask;
    private ConditionVariable waiter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        endpointsAsyncTask = new EndpointsAsyncTask();
        waiter = new ConditionVariable();
    }

    public void testJokeIsNotEmpty() {
        endpointsAsyncTask.execute(this);
        waiter.block();
    }

    @Override
    public void onReceived(String jokeText) {
        assertFalse(TextUtils.isEmpty(jokeText));
        waiter.open();
    }
}
