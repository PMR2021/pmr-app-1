package com.example.pmrapp1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Bundle bdl = this.getIntent().getExtras();
        TextView p2 = findViewById(R.id.pseudo2);
        String pseudo = bdl.getString("pseudo");
        p2.setText(pseudo);
    }
}
