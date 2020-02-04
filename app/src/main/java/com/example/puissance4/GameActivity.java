package com.example.puissance4;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private Button mCol1;
    private Button mCol2;
    private Button mCol3;
    private Button mCol4;
    private Button mCol5;
    private Button mCol6;
    private Button mCol7;

    //Case type: coordonées xy

    //Button 1
    private ImageView mCase11;
    private ImageView mCase12;
    private ImageView mCase13;
    private ImageView mCase14;
    private ImageView mCase15;
    private ImageView mCase16;


    //Button 2
    private ImageView mCase21;
    private ImageView mCase22;
    private ImageView mCase23;
    private ImageView mCase24;
    private ImageView mCase25;
    private ImageView mCase26;

    //Button 3
    private ImageView mCase31;
    private ImageView mCase32;
    private ImageView mCase33;
    private ImageView mCase34;
    private ImageView mCase35;
    private ImageView mCase36;


    //Button 4
    private ImageView mCase41;
    private ImageView mCase42;
    private ImageView mCase43;
    private ImageView mCase44;
    private ImageView mCase45;
    private ImageView mCase46;


    //Button 5
    private ImageView mCase51;
    private ImageView mCase52;
    private ImageView mCase53;
    private ImageView mCase54;
    private ImageView mCase55;
    private ImageView mCase56;


    //Button 6
    private ImageView mCase61;
    private ImageView mCase62;
    private ImageView mCase63;
    private ImageView mCase64;
    private ImageView mCase65;
    private ImageView mCase66;


    //Button 7
    private ImageView mCase71;
    private ImageView mCase72;
    private ImageView mCase73;
    private ImageView mCase74;
    private ImageView mCase75;
    private ImageView mCase76;


    private boolean TourJoueur = true;
    private Object Button;

    public void Apparition(Button col, ImageView C){
        if(TourJoueur) {
            C.setColorFilter(Color.argb(255,255,0,0));
            TourJoueur = false;
        }
        else{
            C.setColorFilter(Color.argb(255,0,0,255));
            TourJoueur = true;
        }
    }

    /*
    public void Clique(final Button col, final ImageView c){
        col.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TourJoueur) {
                    c.setColorFilter(Color.argb(1,255,0,0));
                    TourJoueur = false;
                }
                else{
                    c.setColorFilter(Color.argb(1,0,0,255));
                    TourJoueur = true;
                }

            }
        });
    };*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mCol1 = (Button) findViewById(R.id.button1);
        mCol2 = (Button) findViewById(R.id.button2);
        mCol3 = (Button) findViewById(R.id.button3);
        mCol4 = (Button) findViewById(R.id.button4);
        mCol5 = (Button) findViewById(R.id.button5);
        mCol6 = (Button) findViewById(R.id.button6);
        mCol7 = (Button) findViewById(R.id.button7);

        mCase11 = (ImageView) findViewById(R.id.case11);
        mCase12 = (ImageView) findViewById(R.id.case12);
        mCase13 = (ImageView) findViewById(R.id.case13);
        mCase14 = (ImageView) findViewById(R.id.case14);
        mCase15 = (ImageView) findViewById(R.id.case15);
        mCase16 = (ImageView) findViewById(R.id.case16);

        mCase21 = (ImageView) findViewById(R.id.case21);
        mCase31 = (ImageView) findViewById(R.id.case31);
        mCase41 = (ImageView) findViewById(R.id.case41);
        mCase51 = (ImageView) findViewById(R.id.case51);
        mCase61= (ImageView) findViewById(R.id.case61);
        mCase71 = (ImageView) findViewById(R.id.case71);


       // ArrayList<ImageView> Cases;


        mCol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apparition(mCol1,mCase11);
            }
        });

        mCol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apparition(mCol2,mCase21);
            }
        });

        mCol3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apparition(mCol3,mCase31);
            }
        });






    }
}


