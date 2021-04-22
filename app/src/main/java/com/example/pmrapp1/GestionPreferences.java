package com.example.pmrapp1;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

public class GestionPreferences extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //noinspection deprecation
        addPreferencesFromResource(R.xml.preferences);
    }

    // TODO : comment faire pour vider les préférences lorsque l'on
    // décoche la case depuis cette activité ?
}
