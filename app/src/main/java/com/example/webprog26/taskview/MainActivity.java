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

public class MainActivity extends AppCompatActivity{

    private LeafView mLeafView;
    private static final String TAG = "MainActivity_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLeafView = (LeafView) findViewById(R.id.leafView);
    }
}
