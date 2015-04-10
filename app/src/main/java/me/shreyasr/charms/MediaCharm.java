package me.shreyasr.charms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MediaCharm extends Charm {

    public MediaCharm(int leftMargin, int topMargin) {
        super("Media Charm", R.mipmap.ic_launcher, leftMargin, topMargin);
    }

    public Charm create() {
        return new MediaCharm(0,0);
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
        long time = SystemClock.uptimeMillis();
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
            dispatchedKeyEvent.invoke(audioService, new KeyEvent(time, time, KeyEvent.ACTION_DOWN, keycode, 0));
            dispatchedKeyEvent.invoke(audioService, new KeyEvent(time, time, KeyEvent.ACTION_UP, keycode, 0));
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    static IntentFilter intentFilter;

    static BroadcastReceiver musicReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // AudioManager.isMusicActive();
            Log.d("Received", intent.getAction());
            String action = intent.getAction();
            String cmd = intent.getStringExtra("command");
            Log.v("tag ", action + " / " + cmd);
            String artist = intent.getStringExtra("artist");
            String album = intent.getStringExtra("album");
            String track = intent.getStringExtra("track");
            Log.v("tag", artist + ":" + album + ":" + track);
            Toast.makeText(ApplicationWrapper.getInstance(), track, Toast.LENGTH_SHORT).show();
        }
    };

    static {
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.android.music.metachanged");
        intentFilter.addAction("com.htc.music.metachanged");
        intentFilter.addAction("fm.last.android.metachanged");
        intentFilter.addAction("com.sec.android.app.music.metachanged");
        intentFilter.addAction("com.nullsoft.winamp.metachanged");
        intentFilter.addAction("com.amazon.mp3.metachanged");
        intentFilter.addAction("com.miui.player.metachanged");
        intentFilter.addAction("com.real.IMP.metachanged");
        intentFilter.addAction("com.sonyericsson.music.metachanged");
        intentFilter.addAction("com.rdio.android.metachanged");
        intentFilter.addAction("com.samsung.sec.android.MusicPlayer.metachanged");
        intentFilter.addAction("com.andrew.apollo.metachanged");
        intentFilter.addAction("com.spotify.music.metadatachanged");
    }

    public static void registerMediaReciever(CharmsWindow window) {
        window.registerReceiver(musicReciever, intentFilter);
    }
}
