package com.example.patrichuan.battlequiz;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainMenuScreen extends ActionBarActivity {

    private LinearLayout MainLayout;
    private Button SoloModebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenuscreen_layout);

        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/HVD_Comic_Serif_Pro.otf");

        //Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SoloModebtn = (Button) findViewById(R.id.SoloModebtn);

        SoloModebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SiguienteActivity = new Intent(v.getContext(), SoloModeScreen.class);
                startActivity(SiguienteActivity);
            }
        });

        MainLayout = (LinearLayout) findViewById(R.id.main_layout);
        MainLayout.setBackgroundResource(R.drawable.background);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu_screen, menu);
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
