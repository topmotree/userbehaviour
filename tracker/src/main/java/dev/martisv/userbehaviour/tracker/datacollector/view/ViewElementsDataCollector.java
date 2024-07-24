package dev.martisv.userbehaviour.tracker.datacollector.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.martisv.userbehaviour.tracker.UserBehaviourTracker;
import dev.martisv.userbehaviour.tracker.clickhandler.TouchCoordinates;
import dev.martisv.userbehaviour.tracker.datacollector.DataCollector;
import dev.martisv.userbehaviour.tracker.datacollector.useraction.ClickedElementFinder;
import dev.martisv.userbehaviour.tracker.datacollector.view.metainfo.ViewMetaInfoDictionary;
import dev.martisv.userbehaviour.tracker.datacollector.view.metainfo.ViewMetaProperty;

public class ViewElementsDataCollector implements ViewElementsSaver, DataCollector, ClickedElementFinder {
    private final Context context;
    private final ViewMetaInfoDictionary viewMetaInfoDictionary;

    private ViewElement curentViewElement;

    public ViewElementsDataCollector(Context context, ViewMetaInfoDictionary viewMetaInfoDictionary) {
        this.context = context;
        this.viewMetaInfoDictionary = viewMetaInfoDictionary;
    }

    @Override
    public void saveView(View view) {
        curentViewElement = fromView(view);

        if (UserBehaviourTracker.getOptions().isPrintViewElements()) {
            Log.d("ViewMapDataProvider", curentViewElement.toString());
        }
    }

    private ViewElement fromView(View view) {
        int id = view.getId();
        String type = view.getClass().getSimpleName();

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];

        String elementId = getElementId(id, type, x, y);

        int width = view.getWidth();
        int height = view.getHeight();
        boolean isViewGroup = view instanceof ViewGroup;

        List<ViewMetaProperty> metaProperties = Collections.emptyList();
        if (viewMetaInfoDictionary != null) {
            metaProperties = viewMetaInfoDictionary.get(id);
        }

        List<ViewElement> childElements = null;
        if (isViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            childElements = new ArrayList<>();
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childView = viewGroup.getChildAt(i);
                ViewElement childElement = fromView(childView);
                childElements.add(childElement);
            }
        }

        return new ViewElement(id, elementId, type, x, y, width, height, isViewGroup, metaProperties, childElements);
    }

    private String getElementId(int id, String type, int x, int y) {
        if (id == -1) {
            return type + "_" + x + "_" + y;
        }

        try {
            return context.getResources().getResourceEntryName(id);
        } catch (Exception e) {
            return String.valueOf(id);
        }
    }

    public ViewElement getCurrentViewElement() {
        return curentViewElement;
    }

    @Override
    public String findClickedElementId(TouchCoordinates touchCoordinates) {
        ViewElement childElement = curentViewElement.findChildElement(touchCoordinates);
        if (childElement != null) {
            return childElement.getElementId();
        } else {
            return null;
        }
    }

    @Override
    public String getJsonKey() {
        return "elements";
    }

    @Override
    public JSONArray getJsonArray() {
        List<ViewElement> elements = curentViewElement.getChildElements();
        JSONArray jsonArray = new JSONArray();

        JSONArray elementsJson = new JSONArray();
        for (ViewElement element : elements) {
            try {
                final JSONObject elementJson = element.toJson();
                jsonArray.put(elementJson);
            } catch (Exception e) {
                Log.e("ViewElementsDataCollector", "Error while converting element to JSON", e);
            }
        }

        return jsonArray;
    }
}
