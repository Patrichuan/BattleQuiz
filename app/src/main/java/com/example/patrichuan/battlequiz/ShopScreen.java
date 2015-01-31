package com.example.patrichuan.battlequiz;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import android.widget.Button;

import android.widget.ImageView;
import android.widget.LinearLayout;


import FragmentShop.Cosmetic;
import FragmentShop.Usables;


public class ShopScreen extends ActionBarActivity {
    private LinearLayout MainLayout;
    Usables FragUsable = new Usables();
    Cosmetic FragCosmetic = new Cosmetic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_screen);

        //Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        MainLayout = (LinearLayout) findViewById(R.id.main_layout);
        MainLayout.setBackgroundResource(R.drawable.background);

        //Set opacity on background_coin
        ImageView img = (ImageView)findViewById(R.id.background_coin);
        img.setImageAlpha(85);

        final Button usables = (Button) findViewById(R.id.usablebtn);
        final Button cosmetic = (Button) findViewById(R.id.Cosmeticbtn);
        usables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!FragUsable.isVisible()) {
                    cosmetic.setBackgroundResource(R.drawable.buttonmenu);
                    cosmetic.setTextColor(Color.WHITE);
                    usables.setBackgroundResource(R.drawable.buttonpressed);
                    usables.setTextColor(Color.parseColor("#ce492b"));

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();

                    ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
                    ft.replace(R.id.fragment_place, FragUsable);

                    ft.commit();
                }
                }
        });


        cosmetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!FragCosmetic.isVisible()){
                    usables.setBackgroundResource(R.drawable.buttonmenu);
                    usables.setTextColor(Color.WHITE);
                    cosmetic.setBackgroundResource(R.drawable.buttonpressed);
                    cosmetic.setTextColor(Color.parseColor("#ce492b"));

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();

                    ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
                    ft.replace(R.id.fragment_place, FragCosmetic);

                    ft.commit();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop_screen, menu);
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


