package com.example.pmrapp1;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Reseau extends AppCompatActivity implements View.OnClickListener {
    private static final String CAT ="EDPMR" ;
    Button btnR;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reseau_layout);

        btnR = findViewById(R.id.btnRequete);
        btnR.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // TODO : le déclencher même après
        //  un simple changement en mode avion

        // S'il n'y a pas de reseau, on désactive le bouton
        btnR.setEnabled(verifReseau());

    }

    private String convertStreamToString(InputStream in) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public String requete(String qs) {
        if (qs != null)
        {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            // du point du téléphone virtuel,
            // 127.0.0.1 <=> Adresse IP du téléphone
            // 10.0.2.2 <=> Adresse IP du PC contenant Android Studio
            String urlData = prefs.getString("urlData","http://tomnab.fr/fixture");

            try {
                URL url = new URL(urlData + "?" + qs);
                Log.i(CAT,"url utilisée : " + url.toString());
                HttpURLConnection urlConnection = null;
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = null;
                in = new BufferedInputStream(urlConnection.getInputStream());
                String txtReponse = convertStreamToString(in);
                urlConnection.disconnect();
                return txtReponse;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return "";
    }


    public boolean verifReseau()
    {
        // On vérifie si le réseau est disponible,
        // si oui on change le statut du bouton de connexion
        ConnectivityManager cnMngr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cnMngr.getActiveNetworkInfo();

        String sType = "Aucun réseau détecté";
        Boolean bStatut = false;
        if (netInfo != null)
        {

            NetworkInfo.State netState = netInfo.getState();

            if (netState.compareTo(NetworkInfo.State.CONNECTED) == 0)
            {
                bStatut = true;
                int netType= netInfo.getType();
                switch (netType)
                {
                    case ConnectivityManager.TYPE_MOBILE :
                        sType = "Réseau mobile détecté"; break;
                    case ConnectivityManager.TYPE_WIFI :
                        sType = "Réseau wifi détecté"; break;
                }

            }
        }

        Log.i(CAT,sType);
        return bStatut;
    }

    @Override
    public void onClick(View v) {
        alerter("click btn");
        JSONAsyncTask req = new JSONAsyncTask();
        req.execute("arg1","arg2");

        // EPERM (Operation not permitted) => désinstaller et réinstaller
        // NetworkOnMainThread exception
        // Cf. 'ANR' : Application Not Responding
        // => Faire du multithread
        // Une des solutions : AsyncTask

    }

    private void alerter(String s) {
        Log.i(CAT,s);
        Toast t = Toast.makeText(this,s,Toast.LENGTH_SHORT);
        t.show();
    }

    class JSONAsyncTask extends AsyncTask<String, Void, String> {
        // Params, Progress, Result
        // Exécution par new JSONAsyncTask().execute(param,...)

        @Override
        protected void onPreExecute() {
            // S’exécute dans l’UI Thread
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... qs) {
            // qs[0] contient "arg1"
            // qs[1] contient "arg2"
            // pas d'interaction avec l'UI Thread ici
            String reponse = requete("");
            return reponse;
        }

        protected void onPostExecute(String result) {
            // S’exécute dans l’UI Thread
            Log.i(CAT,result);
        }
    }

}
