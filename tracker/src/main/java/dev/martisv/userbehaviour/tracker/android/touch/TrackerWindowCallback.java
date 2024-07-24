package dev.martisv.userbehaviour.tracker.android.touch;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import androidx.annotation.Nullable;

import dev.martisv.userbehaviour.tracker.android.activity.AndroidTouchEventHandler;

public class TrackerWindowCallback implements Window.Callback {
    final Window.Callback localCallback;
    final AndroidTouchEventHandler touchHandler;

    public TrackerWindowCallback(Window.Callback localCallback, AndroidTouchEventHandler touchHandler) {
        this.localCallback = localCallback;
        this.touchHandler = touchHandler;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return localCallback.dispatchKeyEvent(event);
    }

    @SuppressLint("NewApi")
    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return localCallback.dispatchKeyShortcutEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        touchHandler.onTouch(event);
        return localCallback.dispatchTouchEvent(event);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent event) {
        return localCallback.dispatchTrackballEvent(event);
    }

    @SuppressLint("NewApi")
    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        return localCallback.dispatchGenericMotionEvent(event);
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return localCallback.dispatchPopulateAccessibilityEvent(event);
    }

    @Override
    public View onCreatePanelView(int featureId) {
        return localCallback.onCreatePanelView(featureId);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return localCallback.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        boolean ret = localCallback.onPreparePanel(featureId, view, menu);
        return ret;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return localCallback.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return localCallback.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams attrs) {
        localCallback.onWindowAttributesChanged(attrs);
    }

    @Override
    public void onContentChanged() {
        localCallback.onContentChanged();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.d("", "ttest onfocus changed called");
        localCallback.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onAttachedToWindow() {
        localCallback.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        localCallback.onDetachedFromWindow();
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        localCallback.onPanelClosed(featureId, menu);
    }

    @Override
    public boolean onSearchRequested() {
        return localCallback.onSearchRequested();
    }

    @Override
    public boolean onSearchRequested(SearchEvent searchEvent) {
        return false;
    }

    @SuppressLint("NewApi")
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return localCallback.onWindowStartingActionMode(callback);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        return null;
    }

    @SuppressLint("NewApi")
    @Override
    public void onActionModeStarted(ActionMode mode) {
        localCallback.onActionModeStarted(mode);

    }

    @SuppressLint("NewApi")
    @Override
    public void onActionModeFinished(ActionMode mode) {
        localCallback.onActionModeFinished(mode);
    }
}