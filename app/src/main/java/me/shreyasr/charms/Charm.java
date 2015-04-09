package me.shreyasr.charms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class Charm {

    int leftMargin = 0;
    int rightMargin = 0;

    public Charm(int leftMargin, int rightMargin) {
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
    }

    public abstract View getView(LayoutInflater inflater, ViewGroup parent);
}
