package com.example.puissance4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mBienvenue;
    private EditText mPseudo;
    private Button mJouer;
    private Button mClassement;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBienvenue = (TextView) findViewById(R.id.activity_main_bievenue_txt);
        mPseudo = (EditText) findViewById(R.id.activity_main_pseudo_input);
        mJouer = (Button) findViewById(R.id.activity_main_jouer_btn);
        mClassement = (Button) findViewById(R.id.activity_main_classement_btn);


        mJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Changement d'activité
                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameActivity);
            }
        });

    }


}
