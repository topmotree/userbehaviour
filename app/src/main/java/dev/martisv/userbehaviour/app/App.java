package dev.martisv.userbehaviour.app;

import android.app.Application;

import dev.martisv.userbehaviour.tracker.UserBehaviourTracker;
import dev.martisv.userbehaviour.tracker.data.dataprovider.metadictionary.MetaDictionary;
import dev.martisv.userbehaviour.tracker.data.dataprovider.metadictionary.ViewMetaProperty;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MetaDictionary metaDictionary = new MetaDictionary()
                .add(R.id.recycler_view_button, new ViewMetaProperty("text", "Activity with RecyclerView"), new ViewMetaProperty("color", "purple"))
                .add(R.id.text_view_bottom, new ViewMetaProperty("other", "this is important meta information"));

        new UserBehaviourTracker.Builder(this).setMetaDictionary(metaDictionary).build();
    }
}