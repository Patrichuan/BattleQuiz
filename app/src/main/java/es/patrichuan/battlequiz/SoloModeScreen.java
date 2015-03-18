package es.patrichuan.battlequiz;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;


public class SoloModeScreen extends ActionBarActivity {
    private LinearLayout MainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        setContentView(R.layout.solomodescreen_layout);

        //Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        MainLayout = (LinearLayout) findViewById(R.id.main_layout);
        MainLayout.setBackgroundResource(R.drawable.background);

        // Aplicamos fuente a textos
        Button SuddenDeathbtn = (Button) findViewById(R.id.SuddenDeathbtn);
        Button ClassicModebtn = (Button) findViewById(R.id.ClassicModebtn);
        Button HallOfFamebtn = (Button) findViewById(R.id.HallOfFamebtn);

        FontsOverride.setButtonFont(this, SuddenDeathbtn);
        FontsOverride.setButtonFont(this, ClassicModebtn);
        FontsOverride.setButtonFont(this, HallOfFamebtn);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_solo_mode_screen, menu);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
