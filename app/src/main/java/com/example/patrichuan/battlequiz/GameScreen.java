package com.example.patrichuan.battlequiz;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

// Problema con las puntuaciones en tablets (en esos casos la fuente deberia de ser 40sp y no 20sp)
public class GameScreen extends ActionBarActivity {


    ImageView OcupadoPor, CasillaDe;
    GridView gridView;
    TableroAdapter tableroadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        setContentView(R.layout.gamescreen_layout);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        gridView = (GridView)findViewById(R.id.gridview);
        tableroadapter = new TableroAdapter(this);

        gridView.setAdapter(tableroadapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Prueba del listener: Tag de la casilla
                //String message = "Clicked : " + v.findViewById(R.id.Casilla).getTag();
                //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                // Prueba del listener: Dibujar personaje
                OcupadoPor = (ImageView) v.findViewById(R.id.Ocupante);
                OcupadoPor.setImageResource(R.drawable.seven_personaje_amarillo);

                // Prueba del listener: Colorear casilla
                CasillaDe = (ImageView) v.findViewById(R.id.Casilla);
                CasillaDe.setImageResource(R.drawable.seven_casilla_amarilla);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                GameScreen_Pregunta fragmentPregunta = (GameScreen_Pregunta) fragmentManager.findFragmentByTag("PopUpPregunta");

                // Compruebo si el fragment esta ya creado o no, si no lo esta lo creo
                if (fragmentPregunta == null) {
                    fragmentPregunta = new GameScreen_Pregunta();
                    transaction.add(R.id.child_two, fragmentPregunta, "PopUpPregunta");
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.addToBackStack(null);
                }
                transaction.commit();
            }
        });
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }


    public class TableroAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public TableroAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            StringBuilder NombreCasilla = new StringBuilder("Casilla");
            for (int i = 1; i <= 35; i++) {
                NombreCasilla.append(i);
                items.add(new Item(NombreCasilla.toString(), R.drawable.seven_casilla_gris_borde, R.drawable.empty));
                NombreCasilla = new StringBuilder("Casilla");
            }
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
            Item item = (Item) getItem(i);
            Casilla = (ImageView) v.getTag(R.id.Casilla);
            Casilla.setImageResource(item.drawableCasilla);
            Casilla.setTag(item.name);
            Ocupante = (ImageView) v.getTag(R.id.Ocupante);
            Ocupante.setImageResource(item.drawableOcupante);
            return v;
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

}