package com.example.patrichuan.battlequiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.text.ParseException;

public class LoginScreen extends ActionBarActivity {

    private LinearLayout MainLayout;
    private Button Registerbtn;
    private Button Loginbtn;

    private EditText userEdit;
    private EditText passEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        setContentView(R.layout.loginscreen_layout);

        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/HVD_Comic_Serif_Pro.otf");

        // Aplicamos a Password
        EditText EditPass = (EditText) findViewById(R.id.PassEt);

        FontsOverride.setPasswordFont(this, EditPass);


        // Hay que poner este codigo a un listener de un futuro boton para deslogearse
        //  ParseUser.logOut();
        //  ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null



        Registerbtn = (Button) findViewById(R.id.Registerbtn);
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SiguienteActivity = new Intent(v.getContext(), RegisterScreen.class);
                startActivity(SiguienteActivity);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });

        Loginbtn = (Button) findViewById(R.id.Loginbtn);
        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        //Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        MainLayout = (LinearLayout) findViewById(R.id.main_layout);
        MainLayout.setBackgroundResource(R.drawable.background);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_screen, menu);
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

    public void login() {

        String usernametxt;
        String passwordtxt;
        userEdit = (EditText) findViewById(R.id.UserEt);
        passEdit = (EditText) findViewById(R.id.PassEt);


        // Retrieve the text entered from the EditText
        usernametxt = userEdit.getText().toString().toUpperCase();
        passwordtxt = passEdit.getText().toString().toUpperCase();

        // Send data to Parse.com for verification
        ParseUser.logInInBackground(usernametxt, passwordtxt,
                new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, com.parse.ParseException e) {
                        if (parseUser != null) {
                            Intent SiguienteActivity = new Intent(LoginScreen.this, MainMenuScreen.class);
                            startActivity(SiguienteActivity);
                            Toast.makeText(getApplicationContext(),
                                    "Successfully Logged in",
                                    Toast.LENGTH_LONG).show();
                            finish();

                        } else {

                            //VENTANA EMERGENTE USUARIO INVÁLIDO

                            Toast.makeText(
                                    getApplicationContext(),
                                    "No such user exist, please signup",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

}
