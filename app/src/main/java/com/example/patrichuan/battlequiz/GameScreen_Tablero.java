package com.example.patrichuan.battlequiz;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameScreen_Tablero extends Fragment{

    ImageView OcupadoPor, CasillaDe;
    ViewGroup root;
    GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Will ignore onDestroy Method
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        onRestoreInstanceState(savedInstanceState);
        if(root!=null){
            //((ViewGroup)root.getParent()).removeView(root);
            //return root;
        } else {
            root = (ViewGroup) inflater.inflate(R.layout.gamescreen_tablero_fragment, container, false);
            //root = (ViewGroup) inflater.inflate(R.layout.gamescreen_pregunta_fragment, container, false);
        }

        return root;

        //super.onCreateView(inflater, container, savedInstanceState);
        //root = (ViewGroup) inflater.inflate(R.layout.gamescreen_tablero_fragment, container, false);
        //return root;
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("tablero", (Serializable) gridView);
     }

    //Restore saved data in onSaveInstanceState Bundle
    private void onRestoreInstanceState(Bundle savedInstanceState){
        if(savedInstanceState!=null){
            gridView = (GridView) savedInstanceState.getSerializable("tablero");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridView=(GridView)root.findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapter(getActivity()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // Prueba del listener: Tag de la casilla
                //String message = "Clicked : " + v.findViewById(R.id.Casilla).getTag();
                //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                // Prueba del listener: Dibujar personaje
                OcupadoPor = (ImageView)v.findViewById(R.id.Ocupante);
                OcupadoPor.setImageResource(R.drawable.seven_personaje_amarillo);
                // Prueba del listener: Colorear casilla
                CasillaDe = (ImageView)v.findViewById(R.id.Casilla);
                CasillaDe.setImageResource(R.drawable.seven_casilla_amarilla);

                FragmentTransaction transaction_2 = GameScreen.fragmentManager.beginTransaction();
                transaction_2.replace(R.id.child_two, GameScreen.FragmentPregunta);
                transaction_2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction_2.addToBackStack(null);
                transaction_2.commit();

            }
        });
    }


    private class MyAdapter extends BaseAdapter
    {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public MyAdapter(Context context)
        {
            inflater = LayoutInflater.from(context);
            StringBuilder NombreCasilla = new StringBuilder("Casilla");
            for (int i=1; i<= 35; i++) {
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
        public Object getItem(int i)
        {
            return items.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return items.get(i).drawableCasilla;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View v = view;
            ImageView Casilla;
            ImageView Ocupante;

            if(v == null)
            {
                v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
                v.setTag(R.id.Casilla, v.findViewById(R.id.Casilla));
                v.setTag(R.id.Ocupante, v.findViewById(R.id.Ocupante));
            }

            Item item = (Item)getItem(i);

            Casilla = (ImageView)v.getTag(R.id.Casilla);
            Casilla.setImageResource(item.drawableCasilla);
            Casilla.setTag(item.name);

            Ocupante = (ImageView)v.getTag(R.id.Ocupante);
            Ocupante.setImageResource(item.drawableOcupante);

            return v;
        }

        private class Item
        {
            final String name;
            final int drawableCasilla;
            final int drawableOcupante;

            Item(String name, int drawableCasilla, int drawableOcupante)
            {
                this.name = name;
                this.drawableCasilla = drawableCasilla;
                this.drawableOcupante = drawableOcupante;
            }
        }
    }
}