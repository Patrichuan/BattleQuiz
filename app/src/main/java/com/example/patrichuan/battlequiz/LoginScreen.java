package com.example.patrichuan.battlequiz;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import com.facebook.Session;

import java.util.Arrays;
import java.util.List;

public class LoginScreen extends ActionBarActivity {

    private Dialog progressDialog;

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

        ImageView imagen = (ImageView)findViewById(R.id.protaimg);

        ParseFacebookUtils.initialize(getString(R.string.app_id));

        Registerbtn = (Button) findViewById(R.id.Registerbtn);
        FontsOverride.setButtonFont(this,Registerbtn);
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SiguienteActivity = new Intent(v.getContext(), RegisterScreen.class);
                startActivity(SiguienteActivity);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });

        Loginbtn = (Button) findViewById(R.id.Loginbtn);
        FontsOverride.setButtonFont(this,Loginbtn);
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


        // Aplicamos fuente a textos
        TextView LoginText = (TextView) findViewById(R.id.LoginText);
        TextView lost = (TextView) findViewById(R.id.lost);
        EditText userEdit = (EditText) findViewById(R.id.UserEt);
        EditText passEdit = (EditText) findViewById(R.id.PassEt);

        FontsOverride.setPasswordFont(this, passEdit);
        FontsOverride.setTextViewFont(this, LoginText);
        FontsOverride.setTextViewFont(this, lost);
        FontsOverride.setEditTextFont(this, userEdit);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
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
                            // Remuevo la activity del stack ya que no me interesa que al dar a volver
                            // aparezca la ventana de login de nuevo
                            SiguienteActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(SiguienteActivity);
                            Toast.makeText(getApplicationContext(),
                                    "Successfully Logged in",
                                    Toast.LENGTH_LONG).show();
                            finish();
                            logueado();
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

    public boolean logueado(){
        return true;
    }

    public void onLoginClick(View v) {
        progressDialog = ProgressDialog.show(LoginScreen.this, "", "Logging in...", true);

        List<String> permissions = Arrays.asList("public_profile", "email");
        // NOTE: for extended permissions, like "user_about_me", your app must be reviewed by the Facebook team


        ParseFacebookUtils.logIn(permissions, this, new LogInCallback() {

            @Override
            public void done(ParseUser user, ParseException err) {

               progressDialog.dismiss();
                if (user == null) {
                    Log.d("LoginFacebook", "El usuario cancelo el login");

                } else if (user.isNew()) {

                    // esto es si quieres añadir otras cosas al user, como puntos nivel etc.
                    Log.d("LoginFacebook", "El usuario se registro y logeo con facebook");

                    Intent SiguienteActivity = new Intent(LoginScreen.this, MainMenuScreen.class);
                    // Remuevo la activity del stack ya que no me interesa que al dar a volver
                    // aparezca la ventana de login de nuevo
                    SiguienteActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(SiguienteActivity);
                    Toast.makeText(getApplicationContext(),
                            "Successfully Logged in",
                            Toast.LENGTH_LONG).show();
                    finish();
                    logueado();
                } else {
                    Log.d("LoginFacebook", "Usuario logeado");
                    Intent SiguienteActivity = new Intent(LoginScreen.this, MainMenuScreen.class);
                    // Remuevo la activity del stack ya que no me interesa que al dar a volver
                    // aparezca la ventana de login de nuevo
                    SiguienteActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(SiguienteActivity);
                    Toast.makeText(getApplicationContext(),
                            "Successfully Logged in",
                            Toast.LENGTH_LONG).show();
                    finish();
                    logueado();
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
