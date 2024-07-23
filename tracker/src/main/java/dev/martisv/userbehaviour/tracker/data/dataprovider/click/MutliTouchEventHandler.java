package dev.martisv.userbehaviour.tracker.data.dataprovider.click;

import android.content.Context;
import android.graphics.PointF;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class MutliTouchEventHandler {
    private final int clickActionThreshold;
    private final ClickEventHandler clickEventHandler;
    private final SparseArray<PointF> startPoints = new SparseArray<>();

    public MutliTouchEventHandler(Context context, ClickEventHandler clickEventHandler) {
        this.clickActionThreshold = ViewConfiguration.get(context).getScaledTouchSlop();
        this.clickEventHandler = clickEventHandler;
    }

    public void onTouch(MotionEvent event) {
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
                    if (isClick(startPoint.x, endX, startPoint.y, endY)) {
                        clickEventHandler.onClick(new TouchCoordinates((int) endX, (int) endY));
                    }
                    startPoints.remove(pointerId);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                startPoints.clear();
                break;
        }
    }

    private boolean isClick(float startX, float endX, float startY, float endY) {
        float differenceX = Math.abs(startX - endX);
        float differenceY = Math.abs(startY - endY);
        return !(differenceX > clickActionThreshold || differenceY > clickActionThreshold);
    }
}
