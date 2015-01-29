package com.example.patrichuan.battlequiz;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


// Problema con las puntuaciones en tablets (en esos casos la fuente deberia de ser 40sp y no 20sp)
public class GameScreen extends ActionBarActivity {

    public static GameScreen_Tablero FragmentTablero;
    public static GameScreen_Pregunta FragmentPregunta;
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        setContentView(R.layout.gamescreen_layout);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // FRAGMENTS
        FragmentTablero = new GameScreen_Tablero();
        FragmentPregunta = new GameScreen_Pregunta();
        fragmentManager = getFragmentManager();

        FragmentTransaction transaction_1 = fragmentManager.beginTransaction();
        transaction_1.replace(R.id.child_two, FragmentTablero);
        transaction_1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction_1.addToBackStack(null);
        transaction_1.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_screen, menu);
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
