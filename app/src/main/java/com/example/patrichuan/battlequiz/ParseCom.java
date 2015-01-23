package com.example.patrichuan.battlequiz;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class ParseCom extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "uijAwGOulCsR2k9xqPx4ttZ7mRpr45qmL12HzczS", "NQG9WFZDDOUovoIrnrXIOj9lNSBB8vBRmA0jiAuh");


        // LOGIN Y SIGN UP
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
}
