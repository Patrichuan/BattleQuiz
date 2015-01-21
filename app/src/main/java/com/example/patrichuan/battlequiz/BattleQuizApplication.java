package com.example.patrichuan.battlequiz;

import android.app.Application;
import com.parse.Parse;

public class BattleQuizApplication extends Application {

    @Override
    public void onCreate () {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "uijAwGOulCsR2k9xqPx4ttZ7mRpr45qmL12HzczS", "NQG9WFZDDOUovoIrnrXIOj9lNSBB8vBRmA0jiAuh");
    }
}
