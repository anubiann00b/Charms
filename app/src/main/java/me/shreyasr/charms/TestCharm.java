package me.shreyasr.charms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class TestCharm extends Charm {

    public TestCharm(int leftMargin, int topMargin) {
        super("Test Charm", R.mipmap.ic_launcher, leftMargin, topMargin);
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {
        View charm = inflater.inflate(R.layout.charm_test, parent, false);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) parent.getLayoutParams();
        params.leftMargin = leftMargin;
        params.topMargin = topMargin;
        return charm;
    }
}
