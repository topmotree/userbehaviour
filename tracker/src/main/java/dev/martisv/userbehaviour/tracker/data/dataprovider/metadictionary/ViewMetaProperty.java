package dev.martisv.userbehaviour.tracker.data.dataprovider.metadictionary;

import java.util.List;

public class ViewMetaProperty {
    private String key;
    private String value;

    public ViewMetaProperty(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ViewMetaProperty{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public static String listToString(List<ViewMetaProperty> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).key);
            sb.append(": ");
            sb.append(list.get(i).value);
            if (i != list.size() - 1){
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
