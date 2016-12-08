package com.example.webprog26.taskview;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.webprog26.taskview.custom_view.LeafView;
import com.example.webprog26.taskview.interfaces.OnColorChangeCallback;
import com.example.webprog26.taskview.threads.ViewColorChanger;

public class MainActivity extends AppCompatActivity implements OnColorChangeCallback{

    private LeafView mLeafView;
    private ViewColorChanger mViewColorChanger;
    private static final String TAG = "MainActivity_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLeafView = (LeafView) findViewById(R.id.leafView);
        mViewColorChanger = new ViewColorChanger(this);
        mViewColorChanger.start();

        Button btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewColorChanger.setShouldStop(true);
            }
        });
    }

    @Override
    public void onColorChange(final int color) {
        mLeafView.post(new Runnable() {
            @Override
            public void run() {
                mLeafView.setStartBackgroundColor(color);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mViewColorChanger != null){
            if(mViewColorChanger.isAlive()){
                mViewColorChanger.setShouldStop(true);
                mViewColorChanger.interrupt();
            }
        }
    }
}
