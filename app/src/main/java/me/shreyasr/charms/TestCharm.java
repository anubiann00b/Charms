package me.shreyasr.charms;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class TestCharm extends Charm {

    public TestCharm(int leftMargin, int rightMargin) {
        super(leftMargin, rightMargin);
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {
        View charm = inflater.inflate(R.layout.charm_test, parent, false);
        charm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        return charm;
    }
}
