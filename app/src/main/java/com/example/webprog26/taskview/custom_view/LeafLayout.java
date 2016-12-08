package com.example.webprog26.taskview.custom_view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by webprog26 on 08.12.2016.
 */

public class LeafLayout extends RelativeLayout {

    private static final String TAG = "LeafLayout";
//    private LayoutTransition mLayoutTransition;

    public LeafLayout(Context context) {
        super(context);
        initClickListeners();
//        initTransitions();
    }

    public LeafLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClickListeners();
//        initTransitions();
    }

    public LeafLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initClickListeners();
//        initTransitions();
    }

    private void initClickListeners(){
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick(View view)");
                for(int i = 0; i < LeafLayout.this.getChildCount(); i++){
                    getChildAt(i).setVisibility(GONE);
                }
            }
        });

        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.i(TAG, "onLongClick(View view)");
                for(int i = 0; i < LeafLayout.this.getChildCount(); i++){
                    getChildAt(i).setVisibility(VISIBLE);
                }
                return true;
            }
        });
    }

//    private void initTransitions(){
//        mLayoutTransition = LeafLayout.this.getLayoutTransition();
//        mLayoutTransition.enableTransitionType(LayoutTransition.APPEARING);
//    }
}
