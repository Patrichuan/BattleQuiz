package com.example.patrichuan.battlequiz;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.io.Serializable;

public class GameScreen_Pregunta extends Fragment {

    TextView textviewdeprueba;
    ViewGroup root;
    String texto;

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
            root = (ViewGroup) inflater.inflate(R.layout.gamescreen_pregunta_fragment, container, false);
            //root = (ViewGroup) inflater.inflate(R.layout.gamescreen_pregunta_fragment, container, false);
        }

        return root;
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("texto", textviewdeprueba.getText().toString());
    }

    //Restore saved data in onSaveInstanceState Bundle
    private void onRestoreInstanceState(Bundle savedInstanceState){
        if(savedInstanceState!=null){
            texto = savedInstanceState.getString("texto");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textviewdeprueba =(TextView)root.findViewById(R.id.textviewdeprueba);
        textviewdeprueba.setText("Holaaaaaaaaaaaaaaa");

        textviewdeprueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction_3 = GameScreen.fragmentManager.beginTransaction();
                transaction_3.replace(R.id.child_two, GameScreen.FragmentTablero);
                transaction_3.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction_3.addToBackStack(null);
                transaction_3.commit();

            }
        });
    }
}