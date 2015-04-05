package me.shreyasr.charms;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class CharmHolder extends RelativeLayout {

    public static void addCharm(final ViewGroup root, Charm charm, LayoutInflater inflater) {
        final CharmHolder charmHolder = (CharmHolder) inflater.inflate(R.layout.charms_holder, root, false);
        charmHolder.findViewById(R.id.close_button).setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("Event", "Close Button: " + Utils.eventToString(event));
                root.removeView(charmHolder);
                root.invalidate();
                return true;
            }
        });
        ((FrameLayout)charmHolder.findViewById(R.id.charm_frame)).addView(charm.getView(inflater, charmHolder));
        root.addView(charmHolder);
        root.invalidate();
    }

    public CharmHolder(Context context) {
        super(context);
    }

    public CharmHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CharmHolder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CharmHolder(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    float startX = 0;
    float startY = 0;
    int startWindowX = 0;
    int startWindowY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Log.i("Event", "Holder: " + Utils.eventToString(event));
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) this.getLayoutParams();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getRawX();
                startY = event.getRawY();
                startWindowX = params.leftMargin;
                startWindowY = params.topMargin;
                this.bringToFront();
                break;
            case MotionEvent.ACTION_MOVE:
                params.leftMargin = (int) (event.getRawX() - startX) + startWindowX;
                params.topMargin = (int) (event.getRawY() - startY) + startWindowY;
                this.setLayoutParams(params);
                this.bringToFront();
                break;
        }
        return true;
    }
}
