package dev.martisv.userbehaviour.tracker.utils;

public class TrackerOptions {
    private boolean printClicks;
    private boolean printClickHistory;
    private boolean printViewElements;

    public TrackerOptions(boolean printClicks, boolean printViewElements, boolean printClickHistory) {
        this.printClicks = printClicks;
        this.printClickHistory = printClickHistory;
        this.printViewElements = printViewElements;
    }

    public boolean isPrintClicks() {
        return printClicks;
    }

    public boolean isPrintClickHistory() {
        return printClickHistory;
    }

    public boolean isPrintViewElements() {
        return printViewElements;
    }
}
