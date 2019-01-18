package com.robyapp.android.healthtracker_litfit.Model;

/**
 * Data Model for MainListAdapter
 */
public class MainItem {

    private String Title;
    private String Info;

    // Constructor
    public MainItem(String title, String info) {
        this.Title = title;
        this.Info = info;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }
}
