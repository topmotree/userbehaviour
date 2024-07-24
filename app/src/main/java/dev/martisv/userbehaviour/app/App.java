package dev.martisv.userbehaviour.app;

import android.app.Application;

import dev.martisv.userbehaviour.tracker.UserBehaviourTracker;
import dev.martisv.userbehaviour.tracker.datacollector.view.metainfo.ViewMetaInfoDictionary;
import dev.martisv.userbehaviour.tracker.datacollector.view.metainfo.ViewMetaProperty;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ViewMetaInfoDictionary viewMetaInfoDictionary = new ViewMetaInfoDictionary()
                .add(R.id.recycler_view_button, new ViewMetaProperty("text", "Activity with RecyclerView"), new ViewMetaProperty("color", "purple"))
                .add(R.id.text_view_bottom, new ViewMetaProperty("other", "this is important meta information"));

        new UserBehaviourTracker.Builder(this).setMetaDictionary(viewMetaInfoDictionary).build();
    }
}