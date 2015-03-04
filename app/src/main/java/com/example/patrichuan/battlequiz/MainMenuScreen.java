package com.example.patrichuan.battlequiz;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
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
import android.widget.Toast;

import com.parse.ParseUser;

import java.io.IOException;


public class MainMenuScreen extends ActionBarActivity {

    private LinearLayout MainLayout;
    private Button SoloModebtn, MultiModebtn,Shopbtn, achievementbtn;
    private ImageView SettingsButton, SettingsButton1, SettingsButton2, SettingsButton3;
    private Boolean SettingsDesplegado = false;

    private final String settingsTAG = "settingsTAG";

    private ParseUser currentUser;

    private SharedPreferences GameSettings;
    private SharedPreferences.Editor prefEditor;
    private Boolean FxPuesto, MusicaPuesta, AppRecienLanzada;
    private Boolean HeSalido = false;
    private String musicUri;
    private Uri mUri;

    private static MediaPlayer player = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        setContentView(R.layout.mainmenuscreen_layout);

        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/HVD_Comic_Serif_Pro.otf");

        // Escondo las barras
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        // TOMO LOS DATOS DE LAS SHARED PREFERENCES Y COMPRUEBO SI HE DE PONER MUSICA O NO
        //---------------------------------------------------------------------------------------------------
        //
        try {
            GameSettings = getSharedPreferences(settingsTAG, 0);
            prefEditor = GameSettings.edit();

            FxPuesto = GameSettings.getBoolean("Fx", false);
            MusicaPuesta = GameSettings.getBoolean("Music", false);
            AppRecienLanzada = GameSettings.getBoolean("AppRecienLanzada", false);
            musicUri = GameSettings.getString("Music_uri", null);

            if (AppRecienLanzada) {
                if (musicUri != null) {
                    mUri = Uri.parse(musicUri);

                    if (MusicaPuesta) {
                        player.reset();
                        player.setDataSource(getApplicationContext(), mUri);
                    }

                    player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            if ((MusicaPuesta)&&(!mp.isPlaying())) {
                                mp.start();
                            }
                        }
                    });
                    player.prepareAsync();
                }
            }

        } catch (IOException e) {

        } catch (IllegalStateException e){

        }


        // MENU SETTINGS
        //-----------------------------------------------------------------------------------------------------
        //
        // Instancio los botones del menu settings y asigno los listeners al boton de musica y fx
        SettingsButton = (ImageView) findViewById(R.id.settings_btn);
        SettingsButton1 = (ImageView) findViewById(R.id.fx_btn);
        SettingsButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FxPuesto) {
                    SettingsButton1.setImageResource(R.drawable.seven_settings_nofx_button);
                    prefEditor.putBoolean("Fx", false);
                    prefEditor.commit();
                    FxPuesto = false;
                } else {
                    SettingsButton1.setImageResource(R.drawable.seven_settings_fx_button);
                    prefEditor.putBoolean("Fx", true);
                    prefEditor.commit();
                    FxPuesto = true;
                }
            }
        });
        SettingsButton2 = (ImageView) findViewById(R.id.music_btn);
        SettingsButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MusicaPuesta) {
                    SettingsButton2.setImageResource(R.drawable.seven_settings_nomusic_button);
                    prefEditor.putBoolean("Music", false);
                    prefEditor.commit();
                    player.stop();
                    player.release();
                    MusicaPuesta = false;
                } else {
                    SettingsButton2.setImageResource(R.drawable.seven_settings_music_button);
                    prefEditor.putBoolean("Music", true);
                    prefEditor.commit();
                    // Inicializo el Mediaplayer
                    player = new MediaPlayer();
                    try {
                        if (!player.isPlaying()) {
                            if (!AppRecienLanzada) {
                                mUri = Uri.parse(musicUri);
                            }
                        player.reset();
                        player.setDataSource(getApplicationContext(), mUri);
                        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                    mp.start();
                                }
                        });
                        player.prepareAsync();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    MusicaPuesta = true;
                }
            }
        });
        SettingsButton3 = (ImageView) findViewById(R.id.logout_btn);
        SettingsButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  currentUser = ParseUser.getCurrentUser();
                  if ((currentUser!=null)&&(currentUser.getUsername()!=null)) {
                      Toast.makeText(getApplicationContext(), "User " + currentUser.getUsername() + " Successfully Logged out", Toast.LENGTH_LONG).show();
                      // Deslogeo al usuario y lo mando a la pantalla de login
                      ParseUser.logOut();
                  }
                prefEditor.putBoolean("AppRecienLanzada", false);
                prefEditor.commit();
                // Meter el toast anunciando quien ha sido deslogeado
                Intent SiguienteActivity = new Intent(MainMenuScreen.this, LoginScreen.class);
                SiguienteActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(SiguienteActivity);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
             }
        });

        // Movimiento de ida y vuelta del primer boton del menu de preferencias
        // IDA
        final ObjectAnimator Boton1Ida= ObjectAnimator.ofFloat(SettingsButton1, "translationX", 0f, -100f);
        Boton1Ida.setDuration(200);
        // VUELTA
        final ObjectAnimator Boton1Vuelta= ObjectAnimator.ofFloat(SettingsButton1, "translationX", -100f, 0f);
        Boton1Vuelta.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                SettingsButton1.setImageResource(R.drawable.empty);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        Boton1Vuelta.setDuration(200);

        // Movimiento de ida y vuelta del segundo boton del menu de preferencias
        // IDA
        final ObjectAnimator Boton2Ida= ObjectAnimator.ofFloat(SettingsButton2, "translationX", 0f, -200f);
        Boton2Ida.setDuration(200);
        // VUELTA
        final ObjectAnimator Boton2Vuelta= ObjectAnimator.ofFloat(SettingsButton2, "translationX", -200f, 0f);
        Boton2Vuelta.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                SettingsButton2.setImageResource(R.drawable.empty);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        Boton2Vuelta.setDuration(200);

        // Movimiento de ida y vuelta del tercer boton del menu de preferencias
        // IDA
        final ObjectAnimator Boton3Ida= ObjectAnimator.ofFloat(SettingsButton3, "translationX", 0f, -300f);
        Boton3Ida.setDuration(200);
        // VUELTA
        final ObjectAnimator Boton3Vuelta= ObjectAnimator.ofFloat(SettingsButton3, "translationX", -300f, 0f);
        Boton3Vuelta.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                SettingsButton3.setImageResource(R.drawable.empty);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        Boton3Vuelta.setDuration(200);

        // Listener que despliega o colapsa
        SettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DESPLIEGO
                if (!SettingsDesplegado) {
                    SettingsButton.setImageResource(R.drawable.seven_settings_button);

                    if (FxPuesto) {
                        SettingsButton1.setImageResource(R.drawable.seven_settings_fx_button);
                    } else {
                        SettingsButton1.setImageResource(R.drawable.seven_settings_nofx_button);
                    }

                    if (MusicaPuesta) {
                        SettingsButton2.setImageResource(R.drawable.seven_settings_music_button);
                    } else {
                        SettingsButton2.setImageResource(R.drawable.seven_settings_nomusic_button);
                    }

                    SettingsButton3.setImageResource(R.drawable.seven_settings_logout_button);
                    // Disparo las animaciones para desplegar los botones
                    Boton1Ida.start();
                    Boton2Ida.start();
                    Boton3Ida.start();
                    SettingsDesplegado = true;
                // COLAPSO
                } else {
                    // Disparo las animaciones para contraer los botones
                    Boton1Vuelta.start();
                    Boton2Vuelta.start();
                    Boton3Vuelta.start();
                    SettingsDesplegado = false;
                }
            }
        });
        //-----------------------------------------------------------------------------------------------------


        SoloModebtn = (Button) findViewById(R.id.SoloModebtn);

        SoloModebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefEditor.putBoolean("AppRecienLanzada", false);
                prefEditor.commit();
                Intent SiguienteActivity = new Intent(v.getContext(), GameScreen.class);
                startActivity(SiguienteActivity);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });

        MultiModebtn = (Button) findViewById(R.id.Multiplayerbtn);

        MultiModebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefEditor.putBoolean("AppRecienLanzada", false);
                prefEditor.commit();
                Intent SiguenteActivity = new Intent(v.getContext(), ComingSoon.class);
                startActivity(SiguenteActivity);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });

        Shopbtn = (Button) findViewById(R.id.Shopbtn);

        Shopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefEditor.putBoolean("AppRecienLanzada", false);
                prefEditor.commit();
                Intent SiguenteActivity = new Intent(v.getContext(), ShopScreen.class);
                startActivity(SiguenteActivity);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
            }
        });

        achievementbtn = (Button) findViewById(R.id.Achievementsbtn);

        achievementbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SiguenteActivity = new Intent(v.getContext(), AchievementScreen.class);
                startActivity(SiguenteActivity);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        prefEditor.putBoolean("Fx", true);
        prefEditor.commit();
        prefEditor.putBoolean("Music", true);
        prefEditor.commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        /*
        if (player!=null) {
            if (player.isPlaying()) {
                SettingsButton2.setImageResource(R.drawable.seven_settings_nomusic_button);
                prefEditor.putBoolean("Music", false);
                prefEditor.commit();
                player.stop();
                player.release();
                MusicaPuesta = false;
                HeSalido = true;
            }
        }
        */
    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        if (HeSalido) {
            SettingsButton2.setImageResource(R.drawable.seven_settings_music_button);
            prefEditor.putBoolean("Music", true);
            prefEditor.commit();
            // Inicializo el Mediaplayer
            player = new MediaPlayer();
            try {
                if (!player.isPlaying()) {
                    if (!AppRecienLanzada) {
                        mUri = Uri.parse(musicUri);
                    }
                    player.reset();
                    player.setDataSource(getApplicationContext(), mUri);
                    player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });
                    player.prepareAsync();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            MusicaPuesta = true;
            HeSalido = false;
        }
        */
    }
}
