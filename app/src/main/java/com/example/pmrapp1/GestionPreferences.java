package com.example.pmrapp1;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class GestionPreferences extends PreferenceActivity implements Preference.OnPreferenceChangeListener {
    CheckBoxPreference cbp;
    EditTextPreference edtpl;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        cbp = (CheckBoxPreference) findPreference("remember");
        edtpl = (EditTextPreference) findPreference("login");
        cbp.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Toast t = Toast.makeText(this,"click cb :" +newValue.toString(),Toast.LENGTH_SHORT);
        t.show();
        if (newValue.equals(false)) {
            edtpl.setText("");
        }
        return true;
    }

    // TODO : comment faire pour vider les préférences lorsque l'on
    // décoche la case depuis cette activité ?


}
