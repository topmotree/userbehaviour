package dev.martisv.userbehaviour.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

//TODO remove me
public class ScreenMapUtils {

    private static void collectViewInfo(View view, JSONArray elementsArray) {
        if (view == null || elementsArray == null) {
            return;
        }

        try {
            JSONObject elementInfo = new JSONObject();

            elementInfo.put("type", view.getClass().getSimpleName());
            elementInfo.put("id", view.getId());
            elementInfo.put("x", view.getX());
            elementInfo.put("y", view.getY());
            elementInfo.put("width", view.getWidth());
            elementInfo.put("height", view.getHeight());
            elementsArray.put(elementInfo);

            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    collectViewInfo(viewGroup.getChildAt(i), elementsArray);
                }
            }
        } catch (JSONException e) {
            Log.e("ScreenMapUtils", "Error creating JSON object", e);
        }
    }

    static public JSONArray collectScreenElements(View rootView) {
        JSONArray elementsArray = new JSONArray();
        collectViewInfo(rootView, elementsArray);
        return elementsArray;
    }
}



