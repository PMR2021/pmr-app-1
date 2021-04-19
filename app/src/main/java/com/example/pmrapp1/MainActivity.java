package com.example.pmrapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private String CAT = "EDPMR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(CAT,"onCreate"); // trace d'exécution
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(CAT,"onStart"); // trace d'exécution
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(CAT,"onResume"); // trace d'exécution
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(CAT,"onRestart"); // trace d'exécution
    }

    // TODO : créer les 3 autres méthodes de (re)démarrage de l'activité
    // Cf. cycle de vie des activités dans le poly
    // Associer à chacune une trace d'exécution
    // TODO 2 : mettre des points d'arrêt dans chaque méthode
    // ET exécuter un mode débogage, pas à pas
}