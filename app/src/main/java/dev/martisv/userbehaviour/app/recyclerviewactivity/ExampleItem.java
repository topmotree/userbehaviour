package dev.martisv.userbehaviour.app.recyclerviewactivity;

public class ExampleItem {
    private String number;
    private String title;
    private String subtitle;

    public ExampleItem(String number, String title, String subtitle) {
        this.number = number;
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
