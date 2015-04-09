package me.shreyasr.charms;

import android.content.Context;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.reflect.Method;

public class MediaCharm extends Charm {

    public MediaCharm(int leftMargin, int topMargin) {
        super(leftMargin, topMargin);
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {
        View charm = inflater.inflate(R.layout.charm_media, parent, false);
        charm.findViewById(R.id.media_play_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMediaKey(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE);
            }
        });
        charm.findViewById(R.id.media_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMediaKey(KeyEvent.KEYCODE_MEDIA_PREVIOUS);
            }
        });
        charm.findViewById(R.id.media_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMediaKey(KeyEvent.KEYCODE_MEDIA_NEXT);
            }
        });
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) parent.getLayoutParams();
        params.leftMargin = leftMargin;
        params.topMargin = topMargin;
        return charm;
    }

    private void sendMediaKey(int keycode) {
        long eventtime = SystemClock.uptimeMillis();
        try {
            // Get binder from ServiceManager.checkService(String)
            IBinder iBinder  = (IBinder) Class.forName("android.os.ServiceManager")
                    .getDeclaredMethod("checkService", String.class)
                    .invoke(null, Context.AUDIO_SERVICE);

            // get audioService from IAudioService.Stub.asInterface(IBinder)
            Object audioService  = Class.forName("android.media.IAudioService$Stub")
                    .getDeclaredMethod("asInterface", IBinder.class)
                    .invoke(null, iBinder);

            // Dispatch keyEvent using IAudioService.dispatchMediaKeyEvent(KeyEvent)
            Method dispatchedKeyEvent = Class.forName("android.media.IAudioService").getDeclaredMethod("dispatchMediaKeyEvent", KeyEvent.class);
            dispatchedKeyEvent.invoke(audioService, new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, keycode, 0));
            dispatchedKeyEvent.invoke(audioService, new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_UP, keycode, 0));
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
