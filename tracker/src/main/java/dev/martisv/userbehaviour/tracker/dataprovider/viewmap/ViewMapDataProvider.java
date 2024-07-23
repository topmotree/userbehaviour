package dev.martisv.userbehaviour.tracker.dataprovider.viewmap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewMapDataProvider {
    private ViewInfo curentViewInfo;
    private Context context;

    public ViewMapDataProvider(Context context) {
        this.context = context;
    }

    public void saveViewInfo(View view) {
        curentViewInfo = fromView(context, view);
    }

    //TODO add MetaDictionary to get meta information about view
    private ViewInfo fromView(Context context, View view) {
        int id = view.getId();

        String elementId = context.getResources().getResourceEntryName(id);
        String type = view.getClass().getSimpleName();

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];

        int width = view.getWidth();
        int height = view.getHeight();
        boolean isViewGroup = view instanceof ViewGroup;

        List<ViewInfo> childElements = null;
        if (isViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            childElements = new ArrayList<>();
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childView = viewGroup.getChildAt(i);
                ViewInfo childElement = fromView(context, childView);
                childElements.add(childElement);
            }
        }

        return new ViewInfo(id, elementId, type, x, y, width, height, isViewGroup, childElements);
    }

    public ViewInfo getCurrentView() {
        return curentViewInfo;
    }
}
