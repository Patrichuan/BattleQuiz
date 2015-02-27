package test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.patrichuan.battlequiz.LoginScreen;
import com.example.patrichuan.battlequiz.R;


public class MyTestCase extends ActivityInstrumentationTestCase2<LoginScreen> {
    private LoginScreen actividad;
    private EditText userEt;
    private EditText pass;
    private Button loginbtn;
 //   private Button registerbtn;

    private static final String[] NOMBRES = {"Juanete", "Tomas", "Eva", "Jose"};
    private static final String[] PASS = {"1234", "4321", " ", "Jose"};


    public MyTestCase() {
        super(LoginScreen.class);
    }

    protected void setUp() {
        try {
            super.setUp();
            actividad = getActivity();
            userEt = (EditText) actividad.findViewById(R.id.UserEt);
            pass = (EditText) actividad.findViewById(R.id.PassEt);
            loginbtn = (Button) actividad.findViewById(R.id.Loginbtn);
        //    registerbtn = (Button) actividad.findViewById(R.id.Registerbtn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAddValues() {
        for (int n = 0; n < NOMBRES.length; n++) {
            for (int p = 0; p < PASS.length; p++) {
                TouchUtils.tapView(this, userEt);
                sendKeys(n);
                TouchUtils.tapView(this, pass);
                sendKeys(p);
                TouchUtils.tapView(this, loginbtn);
            }
        }
        assertTrue("Add result should be...", actividad.logueado());
    }
}











