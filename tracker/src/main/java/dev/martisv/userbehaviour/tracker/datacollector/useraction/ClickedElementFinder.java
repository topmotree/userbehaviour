package dev.martisv.userbehaviour.tracker.datacollector.useraction;

import dev.martisv.userbehaviour.tracker.clickhandler.TouchCoordinates;

public interface ClickedElementFinder {
    String findClickedElementId(TouchCoordinates touchCoordinates);
}
