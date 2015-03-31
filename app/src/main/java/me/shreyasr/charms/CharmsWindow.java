package me.shreyasr.charms;

import android.app.Notification;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
        FrameLayout root = (FrameLayout) inflater.inflate(R.layout.charms_window, frame, true);

        ViewGroup charmHolder = (ViewGroup) inflater.inflate(R.layout.charm_holder, root, false);
        View charm = inflater.inflate(R.layout.test_charm, charmHolder, false);
        charmHolder.addView(charm);
        root.addView(charmHolder);
    }

    @Override
    public boolean onTouchBody(int id, Window window, View view, MotionEvent event) {
        this.closeCharmsWindow();
        return false;
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
