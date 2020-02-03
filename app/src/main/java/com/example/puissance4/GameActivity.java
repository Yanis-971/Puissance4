package com.example.puissance4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    private Button mCol1;
    private Button mCol2;
    private Button mCol3;
    private Button mCol4;
    private Button mCol5;
    private Button mCol6;
    private Button mCol7;
    private ImageView mRouge11;
    private ImageView mBleu11;
    private ImageView mRouge12;
    private ImageView mBleu12;
    private ImageView mRouge13;
    private ImageView mBleu13;
    private ImageView mRouge14;
    private ImageView mBleu14;
    private ImageView mRouge15;
    private ImageView mBleu15;
    private ImageView mRouge16;
    private ImageView mBleu16;
    private ImageView mRouge17;
    private ImageView mBleu17;

    private boolean TourJoueur = true;
    private Object Button;

    public void Apparition(Button col, ImageView b,ImageView r){
        if(TourJoueur) {
            b.setVisibility(View.VISIBLE);
            TourJoueur = false;
        }
        else{
            r.setVisibility(View.VISIBLE);
            TourJoueur = true;
        }
    };
/*
    public void Clique(Button col,ImageView b,ImageView r){
        col.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apparition(col,b,r);
            }
        });
    };
 */


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

        mRouge11 = (ImageView) findViewById(R.id.rouge11);
        mBleu11 = (ImageView) findViewById(R.id.bleu11);

        mRouge12 = (ImageView) findViewById(R.id.rouge12);
        mBleu12 = (ImageView) findViewById(R.id.bleu12);

        mRouge13 = (ImageView) findViewById(R.id.rouge13);
        mBleu13 = (ImageView) findViewById(R.id.bleu13);

        mRouge14 = (ImageView) findViewById(R.id.rouge14);
        mBleu14 = (ImageView) findViewById(R.id.bleu14);

        mRouge15 = (ImageView) findViewById(R.id.rouge15);
        mBleu15 = (ImageView) findViewById(R.id.bleu15);

        mRouge16 = (ImageView) findViewById(R.id.rouge16);
        mBleu16 = (ImageView) findViewById(R.id.bleu16);

        mRouge17 = (ImageView) findViewById(R.id.rouge17);
        mBleu17 = (ImageView) findViewById(R.id.bleu17);




        mCol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apparition(mCol1,mBleu11,mRouge11);
            }
        });

        mCol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apparition(mCol2,mBleu12,mRouge12);
            }
        });

        mCol3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apparition(mCol3,mBleu13,mRouge13);
            }
        });


        mCol4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apparition(mCol4,mBleu14,mRouge14);
            }
        });

        mCol5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apparition(mCol5,mBleu15,mRouge15);
            }
        });

        mCol6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apparition(mCol6,mBleu16,mRouge16);
            }
        });

      mCol7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apparition(mCol7,mBleu17,mRouge17);
            }
        });

    }
}


