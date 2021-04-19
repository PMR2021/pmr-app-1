package com.example.pmrapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String CAT = "EDPMR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(CAT,"onCreate"); // trace d'exécution

        Button btnOK = findViewById(R.id.btnOK);
        // Associer un gestionnaire d'événement au clic sur boutonOK
        /* // écouteur anonyme

         btnOK.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 afficherChaine("click2 :" + getPseudo());
             }
         });*/

         // Demande à MainActivity d'implémenter l'interface onClickListener
        btnOK.setOnClickListener(this);
        EditText edtPseudo = findViewById(R.id.pseudo);
        edtPseudo.setOnClickListener(this);
    }

    // TODO : afficher le pseudo dans un toast lors de l'appui sur OK
    // comment associer un gestionnaire d'événement sur btnOK


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(CAT,"onStart"); // trace d'exécution

        // Récupérer une référence vers le EditText "pseudo"
        EditText edtPseudo = findViewById(R.id.pseudo);
        // String p = edtPseudo.toString();
        String p = edtPseudo.getText().toString();

        // afficher un toast avec le contenu du champ de saisie
        Toast myToast = Toast.makeText(this,p, Toast.LENGTH_LONG);
        myToast.show();

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

    public void click1(View view) {
        // Récupérer une référence vers le EditText "pseudo"

        afficherChaine("click1 :" + getPseudo());

    }

    private String getPseudo() {
        EditText edtPseudo = findViewById(R.id.pseudo);
        // String p = edtPseudo.toString();
        return edtPseudo.getText().toString();
    }

    private void afficherChaine(String s){
        // afficher un toast avec le contenu du champ de saisie
        Toast myToast = Toast.makeText(this,s, Toast.LENGTH_LONG);
        myToast.show();

        Log.i(CAT,s);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnOK :
                afficherChaine("click3 :" + getPseudo());
                break;
            case R.id.pseudo :
                afficherChaine("Veuillez entrer votre pseudo");
                break;
        }

    }
}