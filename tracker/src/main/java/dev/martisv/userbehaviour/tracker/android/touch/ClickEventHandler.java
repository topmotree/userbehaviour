package dev.martisv.userbehaviour.tracker.android.touch;

import dev.martisv.userbehaviour.tracker.datacollector.click.TouchCoordinates;

public interface ClickEventHandler {
    void onClick(TouchCoordinates touchCoordinates);
}