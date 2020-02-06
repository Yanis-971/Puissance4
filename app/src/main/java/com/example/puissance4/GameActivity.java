package com.example.puissance4;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
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

    //Case type: coordonées yx{

    //Button 1
    private ImageView mCase11;
    private ImageView mCase12;
    private ImageView mCase13;
    private ImageView mCase14;
    private ImageView mCase15;
    private ImageView mCase16;

    private ArrayList<ImageView> Cases1 = new ArrayList<ImageView>();
    private int c1=0;

    //Button 2
    private ImageView mCase21;
    private ImageView mCase22;
    private ImageView mCase23;
    private ImageView mCase24;
    private ImageView mCase25;
    private ImageView mCase26;

    private ArrayList<ImageView> Cases2 = new ArrayList<ImageView>();
    private int c2=0;

    //Button 3
    private ImageView mCase31;
    private ImageView mCase32;
    private ImageView mCase33;
    private ImageView mCase34;
    private ImageView mCase35;
    private ImageView mCase36;

    private ArrayList<ImageView> Cases3 = new ArrayList<ImageView>();
    private int c3=0;


    //Button 4
    private ImageView mCase41;
    private ImageView mCase42;
    private ImageView mCase43;
    private ImageView mCase44;
    private ImageView mCase45;
    private ImageView mCase46;

    private ArrayList<ImageView> Cases4 = new ArrayList<ImageView>();
    private int c4=0;


    //Button 5
    private ImageView mCase51;
    private ImageView mCase52;
    private ImageView mCase53;
    private ImageView mCase54;
    private ImageView mCase55;
    private ImageView mCase56;

    private ArrayList<ImageView> Cases5 = new ArrayList<ImageView>();
    private int c5=0;


    //Button 6
    private ImageView mCase61;
    private ImageView mCase62;
    private ImageView mCase63;
    private ImageView mCase64;
    private ImageView mCase65;
    private ImageView mCase66;

    private ArrayList<ImageView> Cases6 = new ArrayList<ImageView>();
    private int c6=0;


    //Button 7
    private ImageView mCase71;
    private ImageView mCase72;
    private ImageView mCase73;
    private ImageView mCase74;
    private ImageView mCase75;
    private ImageView mCase76;

    private ArrayList<ImageView> Cases7 = new ArrayList<ImageView>();
    private int c7=0;

////////////////

    private boolean TourJoueur = true;



    public int Apparition(Button col, ArrayList<ImageView> c, int y,int x) {

        if (y < 6) {
            if (TourJoueur) {
                c.get(y).setColorFilter(Color.argb(255, 255, 0, 0));
                tab[y][x]=1;
                TourJoueur = false;
            } else {
                c.get(y).setColorFilter(Color.argb(255, 0, 0, 255));
                tab[y][x]=2;
                TourJoueur = true;
            }
            y++;
        }
        boolean Stop =Gagner(tab);
        if (Stop)
            FinJeu();

        return y;
    }



    public boolean Gagner(int[][] table){
        for (int i = 0; i < table.length; i++) {
            System.out.println("\n");
            for (int j = 0; j < table[i].length; j++) {
                System.out.println(table[i][j]);

                try {
                    if (table[i][j]!= 0){
                        // Vérification vertical
                        if (table[i][j] == table[i + 1][j] && table[i + 1][j] == table[i + 2][j] && table[i + 2][j] == table[i + 3][j])
                            return true;
                            //Vérification horizontal
                        else if (table[i][j] == table[i][j + 1] && table[i][j + 1] == table[i][j + 2] && table[i][j + 2] == table[i][j + 3])
                            return true;
                            //verification diagonale
                        else if (table[i][j] == table[i + 1][j + 1] && table[i + 1][j + 1] == table[i + 2][j + 2] && table[i + 2][j + 2] == table[i + 3][j + 3])
                            return true;
                            //verification diagonale
                        else if (table[i][j] == table[i - 1][j + 1] && table[i - 1][j + 1] == table[i - 2][j + 2] && table[i - 2][j + 2] == table[i - 3][j + 3])
                            return true;
                    }
                }
                catch(Exception e){
                    }

            }
        }
        return false;
    }

    public int[][] tab =new int[6][7];


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


        //Button 1

        Cases1.add(mCase11 = (ImageView) findViewById(R.id.case11));
        Cases1.add(mCase12 = (ImageView) findViewById(R.id.case12));
        Cases1.add(mCase13 = (ImageView) findViewById(R.id.case13));
        Cases1.add(mCase14 = (ImageView) findViewById(R.id.case14));
        Cases1.add(mCase15 = (ImageView) findViewById(R.id.case15));
        Cases1.add(mCase16 = (ImageView) findViewById(R.id.case16));


        //Button 2

        Cases2.add(mCase21 = (ImageView) findViewById(R.id.case21));
        Cases2.add(mCase22 = (ImageView) findViewById(R.id.case22));
        Cases2.add(mCase23 = (ImageView) findViewById(R.id.case23));
        Cases2.add(mCase24 = (ImageView) findViewById(R.id.case24));
        Cases2.add(mCase25 = (ImageView) findViewById(R.id.case25));
        Cases2.add(mCase26 = (ImageView) findViewById(R.id.case26));

        //Button 3

        Cases3.add(mCase31 = (ImageView) findViewById(R.id.case31));
        Cases3.add(mCase32 = (ImageView) findViewById(R.id.case32));
        Cases3.add(mCase33 = (ImageView) findViewById(R.id.case33));
        Cases3.add(mCase34 = (ImageView) findViewById(R.id.case34));
        Cases3.add(mCase35 = (ImageView) findViewById(R.id.case35));
        Cases3.add(mCase36 = (ImageView) findViewById(R.id.case36));


        //Button 4

        Cases4.add(mCase41 = (ImageView) findViewById(R.id.case41));
        Cases4.add(mCase42 = (ImageView) findViewById(R.id.case42));
        Cases4.add(mCase43 = (ImageView) findViewById(R.id.case43));
        Cases4.add(mCase44 = (ImageView) findViewById(R.id.case44));
        Cases4.add(mCase45 = (ImageView) findViewById(R.id.case45));
        Cases4.add(mCase46 = (ImageView) findViewById(R.id.case46));

        //Button 5

        Cases5.add(mCase51 = (ImageView) findViewById(R.id.case51));
        Cases5.add(mCase52 = (ImageView) findViewById(R.id.case52));
        Cases5.add(mCase53 = (ImageView) findViewById(R.id.case53));
        Cases5.add(mCase54 = (ImageView) findViewById(R.id.case54));
        Cases5.add(mCase55 = (ImageView) findViewById(R.id.case55));
        Cases5.add(mCase56 = (ImageView) findViewById(R.id.case56));


        //Button 6

        Cases6.add(mCase61 = (ImageView) findViewById(R.id.case61));
        Cases6.add(mCase62 = (ImageView) findViewById(R.id.case62));
        Cases6.add(mCase63 = (ImageView) findViewById(R.id.case63));
        Cases6.add(mCase64 = (ImageView) findViewById(R.id.case64));
        Cases6.add(mCase65 = (ImageView) findViewById(R.id.case65));
        Cases6.add(mCase66 = (ImageView) findViewById(R.id.case66));


        //Button 7

        Cases7.add(mCase71 = (ImageView) findViewById(R.id.case71));
        Cases7.add(mCase72 = (ImageView) findViewById(R.id.case72));
        Cases7.add(mCase73 = (ImageView) findViewById(R.id.case73));
        Cases7.add(mCase74 = (ImageView) findViewById(R.id.case74));
        Cases7.add(mCase75 = (ImageView) findViewById(R.id.case75));
        Cases7.add(mCase76 = (ImageView) findViewById(R.id.case76));



        mCol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1=Apparition(mCol1,Cases1,c1,0);
            }
        });

        mCol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2=Apparition(mCol2,Cases2,c2,1);
            }
        });

        mCol3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c3=Apparition(mCol3,Cases3,c3,2);
            }
        });

        mCol4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c4=Apparition(mCol4,Cases4,c4,3);
            }
        });

        mCol5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c5=Apparition(mCol5,Cases5,c5,4);
            }
        });

        mCol6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c6=Apparition(mCol6,Cases6,c6,5);
            }
        });

        mCol7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c7=Apparition(mCol7,Cases7,c7,6);

            }

        });





    }


    public void FinJeu(){
        //Boîte de dialogue
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Gagné")
                .setMessage("Le Joueur: " + "Untel" +"  a Gagné!!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Fin de l'activité
                        finish();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        //Fin de l'activité (Cas ou l'user ferme le dialogue sans passer par "ok"
                        finish();
                    }
                })
                .create()
                .show();
    }
}


