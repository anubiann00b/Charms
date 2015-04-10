package me.shreyasr.charms;

import android.app.Notification;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Spinner;

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
        MediaCharm.registerMediaReciever(this);
        ApplicationWrapper.charmsWindow = this;

        final LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final FrameLayout root = (FrameLayout) inflater.inflate(R.layout.charms_window, frame, false);
        root.findViewById(R.id.window_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeCharmsWindow();
            }
        });

        Spinner spinner = (Spinner) root.findViewById(R.id.window_open_spinner);
        CharmSelectAdapter adapter = new CharmSelectAdapter(root, spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(adapter);

        CharmHolder.init(root, inflater);
        frame.addView(root);
    }

    @Override
    public boolean onTouchBody(int id, Window window, View view, MotionEvent event) {
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
        if (ApplicationWrapper.currentState == ApplicationWrapper.State.CLOSED) {
            this.show(ApplicationWrapper.CHARMS_ID);
            ApplicationWrapper.currentState = ApplicationWrapper.State.OPEN;
        }
    }

    public void closeCharmsWindow() {
        if (ApplicationWrapper.currentState == ApplicationWrapper.State.OPEN) {
            this.hide(ApplicationWrapper.CHARMS_ID);
            ApplicationWrapper.currentState = ApplicationWrapper.State.CLOSED;
        }
    }
}
