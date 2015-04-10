package me.shreyasr.charms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class Charm {

    int leftMargin = 0;
    int topMargin = 0;
    public final String name;
    public final int image;

    public Charm(String name, int image, int leftMargin, int topMargin) {
        this.name = name;
        this.image = image;
        this.leftMargin = leftMargin;
        this.topMargin = topMargin;
    }

    public abstract View getView(LayoutInflater inflater, ViewGroup parent);
    public abstract Charm create();
}
