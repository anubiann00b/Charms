package me.shreyasr.charms;

import android.app.Notification;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import wei.mark.standout.StandOutWindow;
import wei.mark.standout.constants.StandOutFlags;
import wei.mark.standout.ui.Window;

public class CharmsWindow extends StandOutWindow {

    @Override
    public String getAppName() {
        return ApplicationWrapper.getInstance().getAppName();
    }

    @Override
    public int getAppIcon() {
        return ApplicationWrapper.getInstance().getAppIcon();
    }

    @Override
    public void createAndAttachView(int id, final FrameLayout frame) {
        ApplicationWrapper.charmsWindow = this;

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        FrameLayout root = (FrameLayout) inflater.inflate(R.layout.charms_window, frame, false);
        frame.addView(root);

        if (root.getChildCount() == 0)
            CharmHolder.addCharm(root, new TestCharm(), inflater);
    }

    @Override
    public boolean onTouchBody(int id, Window window, View view, MotionEvent event) {
        Log.i("Event", "Body: " + Utils.eventToString(event));
        return true;
    }

    @Override
    public StandOutLayoutParams getParams(int id, Window window) {
        return new StandOutLayoutParams(id, ApplicationWrapper.getDisplaySize().x, ApplicationWrapper.getDisplaySize().y, StandOutLayoutParams.LEFT, StandOutLayoutParams.TOP);
    }

    @Override
    public int getFlags(int id) {
        return StandOutFlags.FLAG_WINDOW_FOCUS_INDICATOR_DISABLE;
    }

    @Override
    public Notification getPersistentNotification(int id) {
        return null;
    }

    public void openCharmsWindow() {
        this.show(ApplicationWrapper.CHARMS_ID);
        ApplicationWrapper.currentState = ApplicationWrapper.State.OPEN;
    }

    public void closeCharmsWindow() {
        this.hide(ApplicationWrapper.CHARMS_ID);
        ApplicationWrapper.currentState = ApplicationWrapper.State.CLOSED;
    }
}
