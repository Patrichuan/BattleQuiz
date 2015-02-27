package com.example.patrichuan.battlequiz;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import com.parse.ParseUser;
import android.widget.Button;
import android.widget.EditText;

public class MyTestCaseTest extends ActivityInstrumentationTestCase2<LoginScreen> {

    private Button login;
    private EditText etext1;
    private EditText etext2;
    private static final String Login = "Pat";
    private static final String Password = "Pat";

    private LoginScreen actividad;

    public MyTestCaseTest() {
        super(LoginScreen.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Obtengo la activity actual
        actividad = getActivity();

        // Instancio lo necesario (edittext y button)
        etext1 = (EditText) actividad.findViewById(R.id.UserEt);
        etext2 = (EditText) actividad.findViewById(R.id.PassEt);
        login = (Button) actividad.findViewById(R.id.Loginbtn);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void testAddValues() {

        Boolean UsuarioLogeado = false;

        // Compruebo si el usuario esta logeado
        if (ParseUser.getCurrentUser()!=null) {
            ParseUser.logOut();
        }

        // Meto Pat en Login
        TouchUtils.tapView(this, etext1);
        getInstrumentation().sendStringSync(Login);

        // Meto Pat en Password
        TouchUtils.tapView(this, etext2);
        getInstrumentation().sendStringSync(Password);

        // Clikeo el boton de LogIn
        TouchUtils.clickView(this, login);

        // Guardo el nombre de la actividad en la que me encuentro ahora
        // y si ha ido bien deberia de llamarse "MainMenuScreen"
        if (ParseUser.getCurrentUser()!=null) {
            UsuarioLogeado = true;
        }

        // Comparo el nombre para ver si el resultado es el esperado
        assertTrue("Login result expect to be sucesfull and has been...", UsuarioLogeado == true);
    }
}