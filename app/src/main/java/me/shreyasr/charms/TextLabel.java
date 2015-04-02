package me.shreyasr.charms;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.widget.TextView;

public class TextLabel extends TextView {

    public TextLabel(Context context) {
        super(context);
    }

    public TextLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextLabel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TextLabel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        Log.d("DRAG EVENT", event.toString());
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TOUCH EVENT", event.toString());
        return false;
    }
}
