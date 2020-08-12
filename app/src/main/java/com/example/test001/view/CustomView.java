package com.example.test001.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;

public class CustomView extends View {

    private Context mContext;
    private Scroller mScroller;

    public CustomView(Context context) {
        super(context);
        this.mContext = context;

//        弹性滑动------->      Scroller  +   computeScroll()
//        scrollBy(x,y);
//        scrollTo(x,y);
        mScroller = new Scroller(mContext);
//        向x(0-->10)1000ms滑动
        mScroller.startScroll(0, 0, 10, 0, 1000);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //速度追踪
        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);
        velocityTracker.computeCurrentVelocity(1000);
        velocityTracker.clear();
        velocityTracker.recycle();


//        手势（已弃用）
//        GestureDetector gestureDetector = new GestureDetector(this);

        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        }

    }


    //绘制过程  onMeasure  -->  onLayout  --> onDraw
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
