package me.shreyasr.charms;

import android.view.LayoutInflater;
import android.view.View;

public abstract class Charm {

    public abstract View getView(LayoutInflater inflater, CharmHolder parent);
}
