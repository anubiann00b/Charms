package me.shreyasr.charms;

import android.app.Application;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.view.GestureDetectorCompat;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;

import wei.mark.standout.StandOutWindow;

public class ApplicationWrapper extends Application {

    private static ApplicationWrapper instance;
    public static ApplicationWrapper getInstance() { return instance; }

    enum State { OPEN, CLOSED, OPENING, CLOSING }

    static State currentState = State.CLOSED;

    public static final int WINDOW_ID = 0;
    public static final int LAUNCHER_ID = 1;
    public static GestureDetectorCompat gestureDetector = new GestureDetectorCompat(ApplicationWrapper.getInstance(), new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) { }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) { }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            openCharmsWindow();
            currentState = State.OPENING;
            return true;
        }
    });

    public static Point getDisplaySize() {
        WindowManager wm = (WindowManager) ApplicationWrapper.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= 13) {
            display.getSize(size);
        } else {
            size.x = display.getWidth();
            size.x = display.getHeight();
        }
        return size;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public String getAppName() {
        return "Charms";
    }

    public int getAppIcon() {
        return R.mipmap.ic_launcher;
    }

    public String getPersistentNotificationTitle() {
        return getAppName();
    }

    public String getPersistentNotificationMessage() {
        return this.getString(R.string.close_process);
    }

    public Intent getPersistentNotificationIntent() {
        return StandOutWindow.getCloseAllIntent(this, LauncherWindow.class);
    }

    public Notification getPersistentNotification() {
        Notification.Builder n = new Notification.Builder(this);
        n.setSmallIcon(getAppIcon());
        n.setContentTitle(getPersistentNotificationTitle());
        n.setContentText(getPersistentNotificationMessage());
        n.setContentIntent(PendingIntent.getService(this, 0, getPersistentNotificationIntent(), PendingIntent.FLAG_UPDATE_CURRENT));
        return n.getNotification();
    }

    public SharedPreferences getSharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static void openCharmsWindow() {

    }
}