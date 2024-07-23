package dev.martisv.userbehaviour.tracker.data.dataprovider.viewelement;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.martisv.userbehaviour.tracker.data.dataprovider.metadictionary.MetaDictionary;
import dev.martisv.userbehaviour.tracker.data.dataprovider.metadictionary.ViewMetaProperty;

public class ViewElementsDataProvider {
    private final Context context;
    private final MetaDictionary metaDictionary;

    private ViewElement curentViewElement;

    //TODO how to handle situations in JAVA when MetaDictionary is not provided??
    public ViewElementsDataProvider(Context context, MetaDictionary metaDictionary) {
        this.context = context;
        this.metaDictionary = metaDictionary;
    }

    public void saveViewInfo(View view) {
        curentViewElement = fromView(view);

        //TODO remove log
        Log.d("ViewMapDataProvider", curentViewElement.toString());
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
        if (metaDictionary != null) {
            metaProperties = metaDictionary.get(id);
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

    String getElementId(int id, String type, int x, int y) {
        try {
            return context.getResources().getResourceEntryName(id);
        } catch (Exception e) {
            if (id == -1) {
                return type + "_" + x + "_" + y;
            }
            return String.valueOf(id);
        }
    }

    public ViewElement getCurrentViewElement() {
        return curentViewElement;
    }
}
