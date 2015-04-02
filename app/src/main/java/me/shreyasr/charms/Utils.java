package me.shreyasr.charms;

import android.view.MotionEvent;

public class Utils {

    public static String eventToString(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: return "DOWN";
            case MotionEvent.ACTION_MOVE: return "MOVE";
            case MotionEvent.ACTION_UP: return "UP";
            case MotionEvent.ACTION_CANCEL: return "CANCEL";
            case MotionEvent.ACTION_OUTSIDE: return "OUTSIDE";
            case MotionEvent.ACTION_POINTER_DOWN: return "POINTER DOWN";
            case MotionEvent.ACTION_POINTER_UP: return "POINTER UP";
        }
        return "UNKNOWN";
    }
}
