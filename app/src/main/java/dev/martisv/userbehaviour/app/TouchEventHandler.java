package dev.martisv.userbehaviour.app;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

//TODO Remove this class
public class TouchEventHandler implements View.OnTouchListener {
    private int CLICK_ACTION_THRESHOLD = 200;
    private float startX;
    private float startY;
    public TouchEventHandler(Context context){
        CLICK_ACTION_THRESHOLD = ViewConfiguration.get(context).getScaledTouchSlop();
//        Log.d("TouchEventHandler", "TouchEventHandler: getScaledTouchSlop " + getScaledTouchSlop);
    }


    //TODO why SparseArray??
    private SparseArray<PointF> startPoints = new SparseArray<>();

    //https://stackoverflow.com/a/17836095
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                PointF startPointDown = new PointF(event.getX(pointerIndex), event.getY(pointerIndex));
                startPoints.put(pointerId, startPointDown);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                PointF startPoint = startPoints.get(pointerId);
                if (startPoint != null) {
                    float endX = event.getX(pointerIndex);
                    float endY = event.getY(pointerIndex);
                    if (isAClick(startPoint.x, endX, startPoint.y, endY)) {
                        Log.d("TouchEventHandler", "onTouch: click by pointerId " + pointerId);
                        handleTouchEvent(event, pointerIndex);
                    }
                    startPoints.remove(pointerId);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                startPoints.clear();
                break;
        }

        return true;
    }


    //TODO rename
    private boolean isAClick(float startX, float endX, float startY, float endY) {
        float differenceX = Math.abs(startX - endX);
        float differenceY = Math.abs(startY - endY);
        return !(differenceX > CLICK_ACTION_THRESHOLD/* =5 */ || differenceY > CLICK_ACTION_THRESHOLD);
    }

    private void handleTouchEvent(MotionEvent event, int pointerIndex) {
        Log.d("TouchEventHandler", "handleTouchEvent: " + event.getActionMasked() + " " + pointerIndex);

    }

}
