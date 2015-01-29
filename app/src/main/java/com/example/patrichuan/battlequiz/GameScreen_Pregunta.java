package com.example.patrichuan.battlequiz;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class GameScreen_Pregunta extends Fragment {

    TextView textviewdeprueba;
    ViewGroup root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (root!=null) {
            ((ViewGroup)root.getParent()).removeView(root);
            return root;
        }
        root = (ViewGroup) inflater.inflate(R.layout.gamescreen_pregunta_fragment, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        textviewdeprueba =(TextView)root.findViewById(R.id.textviewdeprueba);
        textviewdeprueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ¿COMO SE RECOBRA EL FRAGMENT DEL TABLERO? NO QUIERO CREARLO DE NUEVO
                
                // Creo un fragmento de pregunta y lo meto en el relative layout de id child_two
                Fragment f1 = new GameScreen_Tablero();
                FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
                ft.replace(R.id.child_two, f1);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }
}