package me.shreyasr.charms;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class CharmHolder extends RelativeLayout {

    static List<Charm> charms = new ArrayList<Charm>();

    public static void init(ViewGroup root, LayoutInflater inflater) {
        charms.clear();
        ComplexPreferences prefs = ApplicationWrapper.getInstance().getSharedPrefs();
        int count = prefs.getInt("count", 0);
        for (int i=0;i<count;i++) {
            Charm c = prefs.getObject("data" + i, Charm.class);
            if (c != null)
                addCharm(root, c, inflater, false);
            else
                Log.d("Charms", "Missing expected charm at " + i + ".");
        }
    }

    public static void save() {
        ComplexPreferences prefs = ApplicationWrapper.getInstance().getSharedPrefs();
        ComplexPreferences.ComplexPreferenceEditor editor = prefs.edit().putInt("count", charms.size());
        for (int i=0;i<charms.size();i++)
            editor.putObject("data" + i, charms.get(i), Charm.class);
        editor.apply();
    }

    public static void addCharm(final ViewGroup root, final Charm charm, LayoutInflater inflater, boolean save) {
        charms.add(charm);
        final CharmHolder charmHolder = (CharmHolder) inflater.inflate(R.layout.charms_holder, root, false);
        charmHolder.setCharm(charm);
        charmHolder.findViewById(R.id.close_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                charms.remove(charm);
                charmHolder.remove(root);
                save();
            }
        });
        ((FrameLayout) charmHolder.findViewById(R.id.charm_frame)).addView(charm.getView(inflater, charmHolder));
        root.addView(charmHolder);
        charmHolder.animateIn();
        if (save)
            save();
    }

    Charm charm;

    public CharmHolder(Context context) {
        super(context);
    }

    public CharmHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CharmHolder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void setCharm(Charm c) {
        this.charm = c;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CharmHolder(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    int dx, dy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) this.getLayoutParams();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dx = (int) event.getX();
                dy = (int) event.getY();
                this.bringToFront();
                this.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                params.leftMargin += (event.getX() - dx);
                params.rightMargin -= (event.getX() - dx);
                params.topMargin += (event.getY() - dy);
                params.bottomMargin -= (event.getY() - dy);
                charm.leftMargin = params.leftMargin;
                charm.topMargin = params.topMargin;
                this.setLayoutParams(params);
                this.bringToFront();
                this.invalidate();
                break;
            case MotionEvent.ACTION_UP:
                save();
                break;
        }
        return true;
    }

    public void animateIn() {
        Animation fadeIn = AnimationUtils.loadAnimation(ApplicationWrapper.getInstance(), R.anim.abc_fade_in);
        fadeIn.setInterpolator(new LinearInterpolator());
        this.startAnimation(fadeIn);
    }

    public void remove(final ViewGroup root) {
        Animation fadeOut = AnimationUtils.loadAnimation(ApplicationWrapper.getInstance(), R.anim.abc_fade_out);
        fadeOut.setInterpolator(new LinearInterpolator());
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                root.removeView(CharmHolder.this);
            }

            @Override public void onAnimationStart(Animation animation) { }
            @Override public void onAnimationRepeat(Animation animation) { }
        });
        this.startAnimation(fadeOut);
    }
}
