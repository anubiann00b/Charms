package me.shreyasr.charms;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class CharmHolder extends FrameLayout {

    public static void addCharm(ViewGroup root, Charm charm, LayoutInflater inflater) {
        CharmHolder charmHolder = (CharmHolder) inflater.inflate(R.layout.charms_holder, root, false);
        charmHolder.addView(charm.getView(inflater, charmHolder));
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("Event", "Holder: " + Utils.eventToString(event));
        return false;
    }
}
