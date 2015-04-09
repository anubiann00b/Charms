package me.shreyasr.charms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class Charm {

    int leftMargin = 0;
    int topMargin = 0;

    public Charm(int leftMargin, int topMargin) {
        this.leftMargin = leftMargin;
        this.topMargin = topMargin;
    }

    public abstract View getView(LayoutInflater inflater, ViewGroup parent);
}
