package com.example.patrichuan.battlequiz;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class RegisterScreen extends ActionBarActivity {

    private Button Registerbtn;
    private LinearLayout MainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerscreen_layout);

        Registerbtn = (Button)findViewById(R.id.Registerbtn);

        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SiguienteActivity = new Intent(v.getContext(), LoginScreen.class);
                startActivity(SiguienteActivity);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        MainLayout = (LinearLayout)findViewById(R.id.main_layout);
        MainLayout.setBackgroundResource(R.drawable.background);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_screen, menu);
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
