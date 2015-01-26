package com.example.patrichuan.battlequiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


public class SplashScreen extends ActionBarActivity {

    private ProgressBar progressBar;
    private Handler progressBarHandler;
    private int progressBarStatus = 0;
    private int maxprogress = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_layout);

        //Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        progressBar = (ProgressBar)findViewById(R.id.ProgressBar);
        progressBarHandler = new Handler();

        // Establezco el % inicial de la barra y el maximo %
        progressBar.setProgress(0);
        progressBar.setMax(maxprogress);

        // Reseteo el % de progreso actual
        progressBarStatus = 0;

        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < maxprogress) {

                    // Aqui llamariamos al metodo para ir cargando datos y en dicho metodo
                    // iriamos devolviendo valores en función de la información que va
                    // quedando por cargar (aqui se suma 5% tan solo por probar)
                    progressBarStatus += 10;

                    // Establezco 1 segundo de intervalo para que no cargue de golpe la barra
                    // y sea visible el aumento del %
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Actualizo la barra
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }

                // Termina de cargar y lanzo la activity login (comprobar si el usuario ya esta logeado comprobando con parse)
                if (progressBarStatus >= maxprogress) {
                        Intent SiguienteActivity = new Intent(SplashScreen.this, LoginScreen.class);
                        startActivity(SiguienteActivity);
                }
            }
        }).start();

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
