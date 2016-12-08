package com.example.webprog26.taskview.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.example.webprog26.taskview.R;
import com.example.webprog26.taskview.interfaces.OnColorChangeCallback;

/**
 * Created by webprog26 on 07.12.2016.
 */

public class LeafView extends View implements OnColorChangeCallback{


    private int mStartBackgroundColor;
    private Paint mPaint;

    private int shapeWidth = 100;
    private int shapeHeight = 100;

    public LeafView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        setupPaint();
    }


    private void initAttrs(AttributeSet attrs){
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.LeafView, 0, 0);

        try {
            this.mStartBackgroundColor = typedArray.getInt(R.styleable.LeafView_startBackgroundColor, Color.GREEN);
        } finally {
            typedArray.recycle();
        }
    }

    private void setupPaint(){
        mPaint = new Paint();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
    }

    public int getStartBackgroundColor() {
        return mStartBackgroundColor;
    }

    public void setStartBackgroundColor(int startBackgroundColor) {
        this.mStartBackgroundColor = startBackgroundColor;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mStartBackgroundColor);
        canvas.drawPath(getTrianglePath(), mPaint);
    }

    protected Path getTrianglePath(){
        Point p1 = new Point(0, shapeHeight), p2 = null, p3 = null;
        p2 = new Point(p1.x + shapeWidth, p1.y);
        p3 = new Point(p1.x + (shapeWidth / 2), p1.y - shapeHeight);
        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);

        return path;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int contentWidth = shapeWidth;

        int minWidth = contentWidth + getPaddingLeft() + getPaddingRight();
        int w = resolveSizeAndState(minWidth, widthMeasureSpec, 0);

        int minHeight = shapeHeight + getPaddingBottom() + getPaddingTop();
        int h = resolveSizeAndState(minHeight, heightMeasureSpec, 0);
        setMeasuredDimension(w, h);
    }

    @Override
    public void onColorChange(int color) {
        setStartBackgroundColor(color);
    }
}
