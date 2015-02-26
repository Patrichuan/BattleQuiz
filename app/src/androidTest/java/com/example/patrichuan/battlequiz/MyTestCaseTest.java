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
    private String ResultadoObtenido;
    private String ResultadoEsperado;

    public MyTestCaseTest() {
        super(LoginScreen.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Obtengo la activity actual
        actividad = getActivity();

        // Guardo el nombre de la actividad en la que estoy
        ResultadoObtenido = actividad.getClass().getName();

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
        ResultadoEsperado = getActivity().getClass().getName();

        // Comparo el nombre para ver si el resultado es el esperado
        assertTrue("Login result has been...", ResultadoEsperado.equals(ResultadoObtenido));
    }
}