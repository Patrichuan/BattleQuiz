package com.example.patrichuan.battlequiz;

import android.app.Application;
import com.parse.Parse;

public class BattleQuizApplication extends Application {

    @Override
    public void onCreate () {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "UsuarioDePrueba1", "Contraseña1");
    }
}
