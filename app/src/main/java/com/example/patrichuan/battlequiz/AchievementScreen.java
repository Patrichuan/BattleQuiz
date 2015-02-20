package com.example.patrichuan.battlequiz;

import android.app.ListActivity;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import Logros.CustomArrayAdapter;
import Logros.Logros;


public class AchievementScreen extends ActionBarActivity {

    private ProgressBar progressBar;
    private TextView textProgressBar;
    private Handler progressBarHandler = new Handler();
    private int progressBarStatus = 0;
    private int maxprogress = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_screen);

        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/HVD_Comic_Serif_Pro.otf");

        //Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ListView listView = (ListView)findViewById(R.id.listView);

        ArrayList<Logros> logrosList=new ArrayList<>();
        //ESTO ES PROVISIONAL SE DEBERA CONSULTAR EN LA BBDD PARA RELLENAR EL ARRAYLIST <-----------------------------------------
        Logros primero,segundo,tercero,cuarto,quinto,sexto;
        primero = new Logros("THE START...","COMPLETE 1 BOARD","0/1");
        segundo = new Logros("OMG THIS GAME IS SO COOL!","COMPLETE 10 BOARDS","0/10");
        tercero = new Logros("OK, I CAN'T STOP","COMPLETE 100 BOARDS","0/100");
        cuarto = new Logros("YOU'RE A MASTER MIND!", "COMPLETE 100 WINS", "0/100");
        quinto = new Logros("YOU DON'T HAVE RIVALS!","COMPLETE 10 WINS CONSECUTIVE","0/10");

        logrosList.add(primero);
        logrosList.add(segundo);
        logrosList.add(tercero);
        logrosList.add(cuarto);
        logrosList.add(quinto);
        logrosList.add(primero);
        logrosList.add(segundo);
        logrosList.add(tercero);
        logrosList.add(cuarto);
        logrosList.add(quinto);
        //----------------------------------------------------------------------------------------------------------------------------


        listView.setAdapter(new CustomArrayAdapter(this, R.layout.achievement_listview_item, logrosList));



        progressBar = (ProgressBar)findViewById(R.id.ProgressBar);
        textProgressBar=(TextView)findViewById(R.id.textProgress);

        textProgressBar.setText(Integer.toString(progressBarStatus)+"/"+ Integer.toString(maxprogress));
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


                            progressBarStatus += 5;


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
                                    textProgressBar.setText(Integer.toString(progressBarStatus)+"/"+ Integer.toString(maxprogress));
                                }
                            });
                        }

                        // Ya se ha cargado el 100% y espero 2 segundos para que el usuario lo vea
                        // y no desaparezca de golpe nada mas llegar
                        if (progressBarStatus == maxprogress) {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_achievement_screen, menu);
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
