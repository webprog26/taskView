package com.example.webprog26.taskview.custom_view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by webprog26 on 08.12.2016.
 */

public class LeafLayout extends RelativeLayout {

    private static final String TAG = "LeafLayout";
    private Map<Integer, Float> mYCoordinatesMap;

    public LeafLayout(Context context) {
        super(context);
        init();
    }

    public LeafLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LeafLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mYCoordinatesMap = new HashMap<>();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick(View view)");
                ObjectAnimator animator;
                int layoutHeight = LeafLayout.this.getHeight();
                int layoutPaddingBottom = LeafLayout.this.getPaddingBottom();
                for(int i = 0; i < LeafLayout.this.getChildCount(); i++){
                    View viewToAnimate = getChildAt(i);
                    int viewToAnimateHeight = viewToAnimate.getHeight();
                    float viewToAnimateY = viewToAnimate.getY();
                    mYCoordinatesMap.put(viewToAnimate.getId(), viewToAnimateY);
                    animator = ObjectAnimator.ofFloat(viewToAnimate, "y", viewToAnimateY, layoutHeight - (viewToAnimateHeight + layoutPaddingBottom));
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.setDuration(4000);
                    animator.start();
                }

            }
        });

        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.i(TAG, "onLongClick(View view)");
                ObjectAnimator animator;
                for(int i = 0; i < LeafLayout.this.getChildCount(); i++){
                    View viewToAnimate = getChildAt(i);
                    animator = ObjectAnimator.ofFloat(viewToAnimate, "y", viewToAnimate.getY(), mYCoordinatesMap.get(viewToAnimate.getId()));
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.setDuration(4000);
                    animator.start();
                }
                return true;
            }
        });
    }
}
