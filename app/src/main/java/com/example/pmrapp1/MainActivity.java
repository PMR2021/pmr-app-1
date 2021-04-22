package com.example.pmrapp1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    // TODO : affiche le menu si la méthode renvoie vrai
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        // R.menu.menu dénote le fichier  res/menu/menu.xml
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // TODO: afficher un toast indiquant quel item a été cliqué
        switch(id) {
            case R.id.menu_account : alerter("Menu : click sur Compte");
                break;
            case R.id.menu_settings :
                //TODO : afficher l'activité GestionPreferences
                alerter("Menu : click sur Préférences");
                Intent iGP = new Intent(this,GestionPreferences.class);
                startActivity(iGP);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void alerter(String s) {
        Log.i(CAT,s);
        Toast t = Toast.makeText(this,s,Toast.LENGTH_SHORT);
        t.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        // Récupérer une référence vers le EditText "pseudo"
        EditText edtPseudo = findViewById(R.id.pseudo);

        // Relire les préférences partagées de l'application
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String l = sp.getString("login","login inconnu");

        // TODO: Insérer la valeur actuelle de la préférence 'login'
        // dans le champ d'édition
        edtPseudo.setText(l);
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
                // Fabrication d'un Bundle de données
                Bundle bdl = new Bundle();
                bdl.putString("pseudo",getPseudo());
                // Changer d'activité
                Intent versSecondAct;
                // Intent explicite
                versSecondAct = new Intent(MainActivity.this,SecondeActivity.class);
                // Ajout d'un bundle à l'intent
                versSecondAct.putExtras(bdl);
                startActivity(versSecondAct);

                break;
            case R.id.pseudo :
                afficherChaine("Veuillez entrer votre pseudo");
                break;
        }

    }
}