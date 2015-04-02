package me.shreyasr.charms;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

public class TestCharm extends Charm {

    @Override
    public View getView(LayoutInflater inflater, CharmHolder parent) {
        View charm = inflater.inflate(R.layout.charm_test, parent, false);
        charm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("Event", "Test Charm: " + Utils.eventToString(event));
                return true;
            }
        });
        return charm;
    }
}
