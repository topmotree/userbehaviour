
package dev.martisv.userbehaviour.tracker.data.dataprovider.viewelement;

import java.util.ArrayList;
import java.util.List;

import dev.martisv.userbehaviour.tracker.data.dataprovider.click.TouchCoordinates;

public class ViewElement {
    private int id;
    private String elementId;
    private String type;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isViewGroup;
    private List<ViewElement> childElements;

    public ViewElement(
            int id,
            String elementId,
            String type,
            int x,
            int y,
            int width,
            int height,
            boolean isViewGroup,
            List<ViewElement> childElements
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

    public ViewElement findChildElement(TouchCoordinates touchCoordinates) {
        if (isWithinBounds(touchCoordinates)) {
            for (ViewElement childElement : childElements) {

                if (childElement.isWithinBounds(touchCoordinates)) {
                    if (childElement.isViewGroup) {
                        ViewElement element = childElement.findChildElement(touchCoordinates);
                        if (element != null) {
                            return element;
                        }
                        // If this is the last child and is a ViewGroup, return null
                        if (childElement.equals(childElements.get(childElements.size() - 1))) {
                            return null;
                        }
                    } else {
                        return childElement;
                    }
                }
            }
        }
        return null;
    }

    public List<ViewElement> getChildElements() {
        List<ViewElement> allChildElements = new ArrayList<>();

        if (this.childElements != null) {
            for (ViewElement child : this.childElements) {
                // Directly add the child if it is not a ViewGroup
                if (!child.isViewGroup()) {
                    allChildElements.add(child);
                } else {
                    // If the child is a ViewGroup, recursively add its non-ViewGroup children
                    List<ViewElement> nonViewGroupChildren = child.getChildElements();
                    for (ViewElement nonViewGroupChild : nonViewGroupChildren) {
                        if (!nonViewGroupChild.isViewGroup()) {
                            allChildElements.add(nonViewGroupChild);
                        }
                    }
                }
            }
        }
        return allChildElements;
    }

    private boolean isWithinBounds(TouchCoordinates touchCoordinates) {
        return touchCoordinates.getX() >= this.x && touchCoordinates.getX() <= this.x + this.width &&
                touchCoordinates.getY() >= this.y && touchCoordinates.getY() <= this.y + this.height;
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

    public int getId() {
        return id;
    }

    public String getElementId() {
        return elementId;
    }

    public String getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isViewGroup() {
        return isViewGroup;
    }
}
