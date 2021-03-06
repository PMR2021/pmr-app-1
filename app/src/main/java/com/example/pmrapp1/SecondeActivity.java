package com.example.pmrapp1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Preconditions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SecondeActivity extends AppCompatActivity {

    private static final String CAT = "EDPMR";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Bundle bdl = this.getIntent().getExtras();
        String chaine_json = bdl.getString("json");

        Log.i(CAT,chaine_json);
        Log.i(CAT,jsonToPrettyFormat(chaine_json));

        Gson gson = new Gson();

        Electif pmr = gson.fromJson(chaine_json,Electif.class);

        Log.i(CAT,pmr.toString());

        // TODO: parcourir l'objet json reçu
        // pour afficher des toasts avec les prénoms
        // de chacun des enseignants

        // structure typique :
        // '{
        // "promo":"2020-2021",
        // "profs":[
        //  {"prenom":"tom","nom":"bou"},
        //  {"prenom":"mohamed","nom":"bou"},
        //  {"prenom":"mathieu","nom":"hau"}
        // ]}'

        try {
            // 1) transformer la chaine en objet JSON
            JSONObject ob_json = new JSONObject(chaine_json);
            // 2) récupérer le tableau des profs
            JSONArray profs = ob_json.getJSONArray("profs");
            // 3) parcourir le tableau
            for(int i=0;i<profs.length();i++) {
                JSONObject nextP = profs.getJSONObject(i);
                // afficher le prénom de chaque prof
                alerter(nextP.getString("prenom"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void alerter(String s) {
        Log.i(CAT,s);
        Toast t = Toast.makeText(this,s,Toast.LENGTH_SHORT);
        t.show();
    }

    public static String jsonToPrettyFormat(String jsonString) {
        JSONObject json = null;
        try {
            json = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();

        return gson.toJson(json);
    }


}
