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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String CAT = "EDPMR";
    private EditText edtPseudo;
    private CheckBox cbRemember;
    private Button btnOK;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(CAT,"onCreate"); // trace d'exécution

        btnOK = findViewById(R.id.btnOK);
        edtPseudo = findViewById(R.id.pseudo);
        cbRemember = findViewById(R.id.cbRemember);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sp.edit();

         // Demande à MainActivity d'implémenter l'interface onClickListener
        btnOK.setOnClickListener(this);
        cbRemember.setOnClickListener(this);
    }

    // affiche le menu si la méthode renvoie vrai
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        // R.menu.menu dénote le fichier  res/menu/menu.xml
        return true;
    }

    // click sur un item du menu
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

        // Relire les préférences partagées de l'application
        Boolean cbR = sp.getBoolean("remember",false);

        // actualise l'état de la case à cocher
        cbRemember.setChecked(cbR);

        // SI la case est cochée, on utilise les préférences pour définir le login
        if (cbRemember.isChecked()) {
            String l = sp.getString("login","login inconnu");
            edtPseudo.setText(l);
        } else {
            // Sinon, le champ doit être vide
            edtPseudo.setText("");
        }

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


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            // TODO : prévoir le cas où la case est clickée
            case R.id.cbRemember :
                alerter("click sur CB");

                // On clique sur la case : il faut mettre à jour les préférences
                editor.putBoolean("remember", cbRemember.isChecked());
                editor.commit();
                if (!cbRemember.isChecked()) {
                    // on supprime le login des préférences
                    editor.putString("login", "");
                    editor.commit();
                }

                break;

            case R.id.btnOK :
                alerter("Click sur btnOK");
                // TODO : si la case est cochée,
                // on enregistre le login dans les préférences
                if (cbRemember.isChecked()) {
                    editor.putString("login", edtPseudo.getText().toString());
                    editor.commit();
                }

                // Fabrication d'un Bundle de données
                Bundle bdl = new Bundle();
                bdl.putString("json",edtPseudo.getText().toString());
                // Changer d'activité
                Intent versSecondAct;
                // Intent explicite
                versSecondAct = new Intent(MainActivity.this,SecondeActivity.class);
                // Ajout d'un bundle à l'intent
                versSecondAct.putExtras(bdl);
                startActivity(versSecondAct);

                break;

            case R.id.pseudo :
                alerter("Veuillez entrer votre pseudo");
                break;
        }

    }
}