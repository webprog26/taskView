package com.example.webprog26.taskview.custom_view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by webprog26 on 08.12.2016.
 */

public class LeafLayout extends RelativeLayout {

    private static final String TAG = "LeafLayout";

    private static final long TRANSLATION_ANIMATION_DURATION = 4000;
    private static final long ROTATION_ANIMATION_DURATION = 8000;
    private static final float ROTATION_ANIMATION_DEGREES = 720;
    private static final String Y_TRANSLATION = "y";
    private static final String ROTATION = "rotation";

    private Map<Integer, Float> mYCoordinatesMap;
    private boolean isDownward = false;

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
                if(isDownward){
                    return;
                }
                ObjectAnimator translationAnimator;
                int layoutHeight = LeafLayout.this.getHeight();
                int layoutPaddingBottom = LeafLayout.this.getPaddingBottom();
                for(int i = 0; i < LeafLayout.this.getChildCount(); i++){
                    View viewToAnimate = getChildAt(i);
                    int viewToAnimateHeight = viewToAnimate.getHeight();
                    float viewToAnimateY = viewToAnimate.getY();
                    mYCoordinatesMap.put(viewToAnimate.getId(), viewToAnimateY);
                    translationAnimator = ObjectAnimator.ofFloat(viewToAnimate, Y_TRANSLATION, viewToAnimateY, layoutHeight - (viewToAnimateHeight + layoutPaddingBottom));
                    translationAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    translationAnimator.setDuration(TRANSLATION_ANIMATION_DURATION);

                    ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(viewToAnimate, ROTATION, viewToAnimate.getX(), ROTATION_ANIMATION_DEGREES);
                    rotationAnimator.setDuration(ROTATION_ANIMATION_DURATION);
                    rotationAnimator.setInterpolator(new OvershootInterpolator());
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.play(translationAnimator).with(rotationAnimator);
                    animatorSet.start();
                }
            isDownward = true;
            }
        });

        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(!isDownward){
                    return false;
                }
                ObjectAnimator animator;
                for(int i = 0; i < LeafLayout.this.getChildCount(); i++){
                    View viewToAnimate = getChildAt(i);
                    animator = ObjectAnimator.ofFloat(viewToAnimate, Y_TRANSLATION, viewToAnimate.getY(), mYCoordinatesMap.get(viewToAnimate.getId()));
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.setDuration(4000);
                    animator.start();
                }
                isDownward = false;
                return true;
            }
        });
    }
}
