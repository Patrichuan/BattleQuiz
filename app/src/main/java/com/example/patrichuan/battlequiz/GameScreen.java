package com.example.patrichuan.battlequiz;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import BBDD.ConnectSQLite;
import BBDD.Querys;
import BBDD.Questions;

public class GameScreen extends ActionBarActivity {

    private GridView gridView;
    private TableroAdapter tableroadapter;
    private View CasillaClickeada = null;
    private View CasillaClickeadaAnterior = null;
    private TextView Marcador;
    private int Puntuacion;
    public boolean PreguntaActiva = false;

    public ConnectSQLite sqLite;
    public Querys querys;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        setContentView(R.layout.gamescreen_layout);

        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/HVD_Comic_Serif_Pro.otf");

        // Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Marcador = (TextView) findViewById(R.id.puntuacionamarilla);
        ActualizarMarcador(0);

        // Creo y abro la base de datos
        sqLite = new ConnectSQLite(this);
        sqLite.createDataBase();
        sqLite.openDataBase();
        querys = new Querys(this);

        gridView = (GridView)findViewById(R.id.gridview);

        // Creo el ArrayList con los datos del tablero y se lo paso al adaptador
        ArrayList<Item> items = new ArrayList<>();
        // Uso un StringBuilder para poner un TAG a cada casilla
        StringBuilder NombreCasilla = new StringBuilder("Casilla");
        for (int i = 1; i <= 35; i++) {
            NombreCasilla.append(i);
            items.add(new Item(NombreCasilla.toString(), R.drawable.seven_casilla_gris_borde, R.drawable.empty));
            // Reseteo el nombre de la casilla
            NombreCasilla = new StringBuilder("Casilla");
        }

        tableroadapter = new TableroAdapter(this, items);
        gridView.setAdapter(tableroadapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                //GameScreen_Pregunta fragmentPregunta = (GameScreen_Pregunta)getFragmentManager().findFragmentByTag("PopUpPregunta");

                if (PreguntaActiva) {
                    // Esta abierta ya una instancia de la ventana de pregunta asi que no hago nada
                } else {
                    // No esta abierta ninguna instancia de pregunta asi que la creo y respondo al listener
                    PreguntaActiva = true;

                    // Si son iguales es que fallé la pregunta
                    if (getCasillaClickeada() == getCasillaClickeadaAnterior()) {
                        setCasillaClickeada(v);
                        setCasillaClickeadaAnterior(v);
                    }

                    // Y si son diferentes es porque acerté y he de avanzar
                    if (getCasillaClickeada() != getCasillaClickeadaAnterior()) {
                        // En caso de haber acertado esto es cierto ya que te mueves
                        setCasillaClickeadaAnterior(getCasillaClickeada());
                        setCasillaClickeada(v);
                    }

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    transaction.add(R.id.main_layout, new GameScreen_Pregunta(), "PopUpPregunta");
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    }
                }
            });
    }

    // Devuelve un numero al azar
    public int NumAlAzar(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    // Devuelve un Question al azar de los presentes en la bae de datos
    public Questions getRandomQuestion () {
        int NumQuestions = querys.countQuestions();
        Questions Pregunta = null;

        if (NumQuestions>0) {
            Pregunta = querys.getQuestionById(NumAlAzar(1,NumQuestions));
        }
        return Pregunta;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_screen, menu);
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


    public void setCasillaClickeada (View Casilla){
        this.CasillaClickeada = Casilla;
    }

    public View getCasillaClickeada () {
        return CasillaClickeada;
    }

    public void setCasillaClickeadaAnterior (View Casilla){
        this.CasillaClickeadaAnterior = Casilla;
    }

    public View getCasillaClickeadaAnterior () {
        return CasillaClickeadaAnterior;
    }

    public void setPuntuacion (int Puntuacion) {
        this.Puntuacion = Puntuacion;
    }

    public int getPuntuacion () {
        return Puntuacion;
    }

    public void ActualizarMarcador (int Puntuacion) {
        Marcador.setText(""+Puntuacion);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }


    public class TableroAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private ArrayList<Item> items;

        public TableroAdapter(Context context, ArrayList<Item> items) {
            inflater = LayoutInflater.from(context);
            this.items = items;
        }

        public ArrayList<Item> getArrayDeItems () {
            return items;
        }

        public void setArrayDeItems (ArrayList<Item> items) {
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return items.get(i).drawableCasilla;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = view;
            ImageView Casilla;
            ImageView Ocupante;
            if (v == null) {
                v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
                v.setTag(R.id.Casilla, v.findViewById(R.id.Casilla));
                v.setTag(R.id.Ocupante, v.findViewById(R.id.Ocupante));
            }

            // A partir de un objeto Item devuelve la vista
            Item item = (Item) getItem(i);

            Casilla = (ImageView) v.getTag(R.id.Casilla);
            Casilla.setImageResource(item.drawableCasilla);
            Casilla.setTag(item.name);

            Ocupante = (ImageView) v.getTag(R.id.Ocupante);
            Ocupante.setImageResource(item.drawableOcupante);

            return v;
        }
    }

    private class Item {
        final String name;
        final int drawableCasilla;
        final int drawableOcupante;

        Item(String name, int drawableCasilla, int drawableOcupante) {
            this.name = name;
            this.drawableCasilla = drawableCasilla;
            this.drawableOcupante = drawableOcupante;
        }
    }
}