package me.shreyasr.charms;

import android.graphics.Point;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class CharmHolder {

    Charm charm;
    private int x;
    private int y;
    private int width;
    private int height;
    FrameLayout charmHolder = null;

    public CharmHolder(Charm charm, int x, int y, int width, int height) {
        this.charm = charm;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void addView(LayoutInflater inflater, FrameLayout window) {
        charmHolder = (FrameLayout) inflater.inflate(R.layout.charms_holder, window, true);
        View charmView = charm.getView(inflater, charmHolder);

        setLayoutParams();

        charmView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DRAG_LOCATION) {
                    x = (int) event.getX();
                    y = (int) event.getY();
                    setLayoutParams();
                }
                Log.d("DRAG CALLBACK", event.toString());
                return false;
            }
        });
        charmView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TOUCH CALLBACK", event.toString());
                return false;
            }
        });
    }

    void setLayoutParams() {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) charmHolder.getLayoutParams();
        Point size = ApplicationWrapper.getDisplaySize();
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.leftMargin = x;
        params.topMargin = y;
        params.width = Math.min(size.x - params.leftMargin, width);
        params.height = Math.min(size.y - params.topMargin, height);
        charmHolder.setLayoutParams(params);
        charmHolder.invalidate();
    }
}
