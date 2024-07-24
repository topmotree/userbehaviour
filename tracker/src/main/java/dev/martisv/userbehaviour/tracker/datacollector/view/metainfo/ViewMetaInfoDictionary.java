package dev.martisv.userbehaviour.tracker.datacollector.view.metainfo;

import androidx.annotation.IdRes;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewMetaInfoDictionary {
    private final Map<Integer, List<ViewMetaProperty>> metaDictionary = new HashMap<>();

    public ViewMetaInfoDictionary add(@IdRes Integer id, ViewMetaProperty... properties) {
        metaDictionary.put(id, Arrays.asList(properties));
        return this;
    }

    public List<ViewMetaProperty> get(@IdRes Integer id) {
        return metaDictionary.get(id) != null ? metaDictionary.get(id) : Collections.emptyList();
    }
}
