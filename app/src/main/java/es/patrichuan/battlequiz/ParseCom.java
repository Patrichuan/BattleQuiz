package es.patrichuan.battlequiz;

import android.app.Application;

import com.parse.Parse;

public class ParseCom extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "QefcH5aijsgPQAEVEgTTNTAEWe85qfApZqItuQEy", "JdPTfLINCT4JP7wtZ0nQYaXHxsjYu3aTB4hMUPAT");

        // LOGIN Y SIGN UP
        //ParseUser.enableAutomaticUser();
        //ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        //defaultACL.setPublicReadAccess(true);

        //ParseACL.setDefaultACL(defaultACL, true);
    }
}
