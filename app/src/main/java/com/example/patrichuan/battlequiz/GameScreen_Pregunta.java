package com.example.patrichuan.battlequiz;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GameScreen_Pregunta extends Fragment {
    TextView textviewdeprueba;
    ViewGroup root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        root = (ViewGroup) inflater.inflate(R.layout.gamescreen_pregunta_fragment, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textviewdeprueba = (TextView) root.findViewById(R.id.texto);
        textviewdeprueba.setText("Fragment Pregunta LANZADO !!");
        textviewdeprueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si lo clikeo me lo cargo (lo saco de la pila)
                getFragmentManager().popBackStack();
            }
        });
    }
}