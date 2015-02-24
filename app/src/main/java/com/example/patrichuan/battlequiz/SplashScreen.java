package com.example.patrichuan.battlequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashScreen extends ActionBarActivity {

    private ProgressWheel progressWheel;
    private TextView value;
    private Intent SiguienteActivity;
    private ParseUser currentUser;
    private ConnectSQLite sqLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_layout);

        sqLite = new ConnectSQLite(this);
        sqLite.createDataBase();
        sqLite.openDataBase();

        //Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Poner otra flag para que no se quede en el historial
        progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);
        value = (TextView) findViewById(R.id.value_tv);


        progressWheel.setProgress(0.0f);
        progressWheel.setCallback(new ProgressWheel.ProgressCallback() {
            @Override
            public void onProgressUpdate(float progress) {
                if (progress == 0) {
                    progressWheel.setProgress(1.0f);
                } else if (progress == 1.0f) {
                    Intent SiguienteActivity = new Intent(SplashScreen.this, LoginScreen.class);
                    currentUser = ParseUser.getCurrentUser();

                //    if (currentUser != null) {
                       // do stuff with the user
                //        SiguienteActivity = new Intent(SplashScreen.this, MainMenuScreen.class);
                //    } else {
                        // show the signup or login screen
                        SiguienteActivity = new Intent(SplashScreen.this, LoginScreen.class);
                //    }
                    // Remuevo la activity del stack ya que no me interesa que al dar a volver
                    // aparezca la ventana de login de nuevo
                    SiguienteActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(SiguienteActivity);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                }
                value.setText(String.format("%.0f", progress*100)+"%");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
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
