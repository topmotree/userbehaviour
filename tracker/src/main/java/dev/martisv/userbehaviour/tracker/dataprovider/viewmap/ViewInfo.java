
package dev.martisv.userbehaviour.tracker.dataprovider.viewmap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewInfo {
    private int id;
    private String elementId;
    private String type;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isViewGroup;
    private List<ViewInfo> childElements;

    public ViewInfo(
            int id,
            String elementId,
            String type,
            int x,
            int y,
            int width,
            int height,
            boolean isViewGroup,
            List<ViewInfo> childElements
    ) {
        this.id = id;
        this.elementId = elementId;
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isViewGroup = isViewGroup;
        this.childElements = childElements;
    }

    public ViewInfo find(int x, int y) {
        if (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height) {

            for (ViewInfo childElement : childElements) {
                if (x >= childElement.x && x <= childElement.x + childElement.width &&
                        y >= childElement.y && y <= childElement.y + childElement.height) {
                    if (childElement.isViewGroup) {
                        ViewInfo viewGroupChildElement = childElement.find(x, y);

                        if (viewGroupChildElement != null) {
                            return viewGroupChildElement;
                        } else {
                            return childElement;
                        }
                    } else {
                        return childElement;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ViewInfo{" +
                "id=" + id +
                ", elementId='" + elementId + '\'' +
                ", type='" + type + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", isViewGroup=" + isViewGroup +
                ", childElements=" + childElements +
                '}';
    }
}
