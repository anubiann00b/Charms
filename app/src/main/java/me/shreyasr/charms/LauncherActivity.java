package me.shreyasr.charms;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import wei.mark.standout.StandOutWindow;


public class LauncherActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        StandOutWindow.closeAll(this, LauncherWindow.class);
        StandOutWindow.closeAll(this, CharmsWindow.class);
        StandOutWindow.show(this, LauncherWindow.class, ApplicationWrapper.LAUNCHER_ID);
        StandOutWindow.show(this, CharmsWindow.class, ApplicationWrapper.CHARMS_ID);
        StandOutWindow.hide(this, CharmsWindow.class, ApplicationWrapper.CHARMS_ID);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launcher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
