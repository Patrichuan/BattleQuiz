package com.example.patrichuan.battlequiz;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameScreen_Pregunta extends Fragment {

    private Button BotonRojo, BotonVerde, BotonAmarillo, BotonAzul;
    private ImageView OcupadoPor, CasillaDe;
    private ImageView OcupadoPor_Anterior, CasillaDe_Anterior;

    private GameScreen ActividadPadre;
    private View CasillaClickeada;
    private View CasillaClickeadaAnterior;

    private int TiempoRestante = 15;
    private TextView CuentaAtras;

    final Handler myHandler = new Handler();
    final Timer myTimer = new Timer();

    private ViewGroup root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Activity Padre
        ActividadPadre = (GameScreen) getActivity();

        // Casilla Clickeada
        CasillaClickeada = ActividadPadre.getCasillaClickeada();
        CasillaClickeadaAnterior = ActividadPadre.getCasillaClickeadaAnterior();

        root = (ViewGroup) inflater.inflate(R.layout.gamescreen_pregunta_fragment, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Instancio el texto de la Cuenta Atras
        CuentaAtras = (TextView) root.findViewById(R.id.CuentaAtras);

        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                UpdateGUI();
                if (TiempoRestante==0) {
                    CloseFragment ();
                }
            }
        }, 0, 1000);

        // Prueba del listener: Dibujar personaje
        OcupadoPor = (ImageView) CasillaClickeada.findViewById(R.id.Ocupante);
        // Prueba del listener: Colorear casilla
        CasillaDe = (ImageView) CasillaClickeada.findViewById(R.id.Casilla);

        // Prueba del listener: Dibujar personaje
        OcupadoPor_Anterior = (ImageView) CasillaClickeadaAnterior.findViewById(R.id.Ocupante);
        // Prueba del listener: Colorear casilla
        CasillaDe_Anterior = (ImageView) CasillaClickeadaAnterior.findViewById(R.id.Casilla);

        BotonRojo = (Button) root.findViewById(R.id.BotonRojo);
        BotonVerde = (Button) root.findViewById(R.id.BotonVerde);
        BotonAmarillo = (Button) root.findViewById(R.id.BotonAmarillo);
        BotonAzul = (Button) root.findViewById(R.id.BotonAzul);

        // Crear un metodo que devuelva si es correcta o no y cierre la ventana
        BotonRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseFragment ();
            }
        });

        BotonVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Nueva Casilla
                OcupadoPor.setImageResource(R.drawable.seven_personaje_amarillo);
                CasillaDe.setImageResource(R.drawable.seven_casilla_amarilla);

                // Casilla Anterior
                OcupadoPor_Anterior.setImageResource(R.drawable.empty);
                CasillaDe_Anterior.setImageResource(R.drawable.seven_casilla_amarilla);

                // Sumo 10 puntos al marcador por acertar
                ActividadPadre.setPuntuacion(ActividadPadre.getPuntuacion() + 10);
                ActividadPadre.ActualizarMarcador(ActividadPadre.getPuntuacion());

                CloseFragment ();
            }
        });

        BotonAmarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseFragment ();
            }
        });

        BotonAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseFragment ();
            }
        });
    }

    private void CloseFragment () {
        ActividadPadre.PreguntaActiva = false;

        StopCountdown ();

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(this);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void UpdateGUI() {
        if (TiempoRestante>0) {
            TiempoRestante--;
            myHandler.post(myRunnable);
        }
    }

    Runnable myRunnable = new Runnable() {
        public void run() {
            if (TiempoRestante<=9) {
                CuentaAtras.setText("0" + TiempoRestante );
            } else {
                CuentaAtras.setText(""+ TiempoRestante);
            }
        }
    };

    // Para el hilo para que deje de contar y lo purga
    public void StopCountdown () {
        myTimer.cancel();
        myTimer.purge();
    }
}