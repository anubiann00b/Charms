package me.shreyasr.charms;

import android.app.Application;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;

import wei.mark.standout.StandOutWindow;

public class ApplicationWrapper extends Application {

    private static ApplicationWrapper instance;
    public static ApplicationWrapper getInstance() { return instance; }

    public enum State { OPEN, CLOSED, OPENING, CLOSING }

    public static State currentState = State.CLOSED;

    public static CharmsWindow charmsWindow = null;

    public static final int CHARMS_ID = 0;
    public static final int LAUNCHER_ID = 1;
    public static GestureDetectorCompat gestureDetector = new GestureDetectorCompat(ApplicationWrapper.getInstance(), new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent event) {
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
            ApplicationWrapper.charmsWindow.openCharmsWindow();
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

    private Notification persistentNotification = null;

    public String getAppName() {
        return "Charms";
    }

    public int getAppIcon() {
        return R.mipmap.ic_launcher;
    }

    public Notification getPersistentNotification() {
        if (persistentNotification == null) {
            Notification.Builder n = new Notification.Builder(this);
            n.setSmallIcon(this.getAppIcon());
            n.setContentTitle(this.getAppName());
            n.setContentIntent(PendingIntent.getService(this, 0, StandOutWindow.getCloseAllIntent(this, LauncherWindow.class), PendingIntent.FLAG_UPDATE_CURRENT));
            persistentNotification = n.getNotification();
        }
        return persistentNotification;
    }

    public ComplexPreferences getSharedPrefs() {
        return ComplexPreferences.getComplexPreferences(this);
    }
}