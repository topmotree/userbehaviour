package dev.martisv.userbehaviour.tracker.data.dataprovider.viewelement;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewElementsDataProvider {
    private ViewElement curentViewElement;
    private Context context;

    public ViewElementsDataProvider(Context context) {
        this.context = context;
    }

    public void saveViewInfo(View view) {
        curentViewElement = fromView(context, view);

        //TODO remove log
        Log.d("ViewMapDataProvider", curentViewElement.toString());
    }

    //TODO add MetaDictionary to get meta information about view
    private ViewElement fromView(Context context, View view) {
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

        List<ViewElement> childElements = null;
        if (isViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            childElements = new ArrayList<>();
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childView = viewGroup.getChildAt(i);
                ViewElement childElement = fromView(context, childView);
                childElements.add(childElement);
            }
        }

        return new ViewElement(id, elementId, type, x, y, width, height, isViewGroup, childElements);
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
