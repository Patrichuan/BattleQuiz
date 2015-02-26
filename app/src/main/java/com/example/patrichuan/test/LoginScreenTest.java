package com.example.patrichuan.test;

/**
 * Created by daniel on 26/02/15.
 */
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.patrichuan.battlequiz.LoginScreen;
import com.example.patrichuan.battlequiz.R;

public class LoginScreenTest extends ActivityInstrumentationTestCase2<LoginScreen> {
    private TextView etiqueta;
    private Button entrar;
    private EditText etext1;
    private EditText etext2;
    private LoginScreen login;

    public LoginScreenTest() {
        super(LoginScreen.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        login = getActivity();
        etext1 = (EditText) login.findViewById(R.id.UserEt);
        etext2 = (EditText) login.findViewById(R.id.PassEt);
        entrar = (Button) login.findViewById(R.id.Loginbtn);

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private static final String USER = "JOSE";
    private static final String PASSWORD = "JOSE";


    public void testAddValues() {

        //on value 1 entry
        TouchUtils.tapView(this, etext1);
        getInstrumentation().sendStringSync(USER);
        // now on value2 entry
        TouchUtils.tapView(this, etext2);
        getInstrumentation().sendStringSync(PASSWORD);
        // now on Add button
        TouchUtils.clickView(this, entrar);

        assertTrue("Add result should be...", login.logueado());
    }


}
