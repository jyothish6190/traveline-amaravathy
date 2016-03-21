package com.example.jyothish.material_design.model;

/**
 * Created by Jyothish on 09-03-2016.
 */
public class NavDrawerItem {
    private static final String TAG = "NavDrawerItem";

    private boolean showNotify = false;
    private String title ;

    public NavDrawerItem() {
    }

    public NavDrawerItem(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
