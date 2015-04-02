package me.shreyasr.charms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestCharm extends Charm {

    @Override
    public View getView(LayoutInflater inflater, ViewGroup charmHolder) {
        View charm = inflater.inflate(R.layout.charm_test, charmHolder, true);
        return charm;
    }
}
