package com.example.yoshimurasatoshi.kadai08;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onButton1Click(View view){
        getFragmentManager().beginTransaction()
                .add(R.id.container, new PlaceholderFragment())
                .commit();
    }

//    public void onDeleteButtonClick(View view ){
//        getFragmentManager().beginTransaction()
//                .remove(R.id.container, new PlaceholderFragment())
//                .commit();
//    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        Timer mTimer;
        final long start = SystemClock.elapsedRealtime();
        android.os.Handler mHandler = new android.os.Handler();
        TextView mTextView;

        boolean timerLive = true;

        public PlaceholderFragment() {
        }

        public void removeThis(){
            getFragmentManager().beginTransaction()
                    .remove(this)
                    .commit();
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            mTextView = (TextView)view.findViewById(R.id.helloworld1);

            Button b  = (Button)getView().findViewById(R.id.deleteButton);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(timerLive){
                        mTimer.cancel();
                        timerLive = false;
                    }else{
                        Log.w("反応","してますか");
                        removeThis();
                    }
                }
            });

            mTimer = new Timer(true);
            mTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            long t = SystemClock.elapsedRealtime()  - start;
                            Date d = new Date(t);
                            SimpleDateFormat f = new SimpleDateFormat("mm:ss.SSS");
                            final String s = f.format(d);
                            Log.i("aa","bb" + s);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mTextView.setText(s);
                                }
                            });
                        }
                    },
                0,
                500
            );

        }

        //気をつける点
        //textView1は複数になるのでフラグメントも指定する
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my, container, false);
            return rootView;
        }

    }

}
