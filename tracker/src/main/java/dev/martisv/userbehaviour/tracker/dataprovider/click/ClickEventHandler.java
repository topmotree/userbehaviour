package dev.martisv.userbehaviour.tracker.dataprovider.click;

import dev.martisv.userbehaviour.tracker.TouchCoordinates;

public interface ClickEventHandler {
    void onClick(TouchCoordinates touchCoordinates);
}