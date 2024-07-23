package dev.martisv.userbehaviour.tracker.data.saver.dto;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dev.martisv.userbehaviour.tracker.data.dataprovider.metadictionary.ViewMetaProperty;
import dev.martisv.userbehaviour.tracker.data.dataprovider.viewelement.ViewElement;

public class ElementDto {
    private String type;
    private String id;
    private int x;
    private int y;
    private int width;
    private int height;
    private String meta;

    public ElementDto(String type, String id, int x, int y, int width, int height, String metaProperties) {
        this.type = type;
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.meta = metaProperties;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("id", id);
        json.put("x", x);
        json.put("y", y);
        json.put("width", width);
        json.put("height", height);
        json.put("meta", meta);
        return json;
    }

    public static List<ElementDto> fromViewElements(List<ViewElement> screenElements) {
        List<ElementDto> elementDtos = new ArrayList<>();
        if (screenElements != null) {
            for (ViewElement element : screenElements) {
                ElementDto dto = fromViewElement(element);
                elementDtos.add(dto);
            }
        }
        return elementDtos;
    }

    public static ElementDto fromViewElement(ViewElement element) {
        return new ElementDto(
                element.getType(),
                element.getElementId(),
                element.getX(),
                element.getY(),
                element.getWidth(),
                element.getHeight(),
                ViewMetaProperty.listToString(element.getMetaProperties())
        );
    }
}
