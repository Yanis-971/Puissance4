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
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;


import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import model.User;

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
    public int c1=0;

    //Button 2
    private ImageView mCase21;
    private ImageView mCase22;
    private ImageView mCase23;
    private ImageView mCase24;
    private ImageView mCase25;
    private ImageView mCase26;

    private ArrayList<ImageView> Cases2 = new ArrayList<ImageView>();
    public int c2=0;

    //Button 3
    private ImageView mCase31;
    private ImageView mCase32;
    private ImageView mCase33;
    private ImageView mCase34;
    private ImageView mCase35;
    private ImageView mCase36;

    private ArrayList<ImageView> Cases3 = new ArrayList<ImageView>();
    public int c3=0;


    //Button 4
    private ImageView mCase41;
    private ImageView mCase42;
    private ImageView mCase43;
    private ImageView mCase44;
    private ImageView mCase45;
    private ImageView mCase46;

    private ArrayList<ImageView>  Cases4 = new ArrayList<ImageView>();
    public int c4=0;


    //Button 5
    private ImageView mCase51;
    private ImageView mCase52;
    private ImageView mCase53;
    private ImageView mCase54;
    private ImageView mCase55;
    private ImageView mCase56;

    private ArrayList<ImageView> Cases5 = new ArrayList<ImageView>();
    public int c5=0;


    //Button 6
    private ImageView mCase61;
    private ImageView mCase62;
    private ImageView mCase63;
    private ImageView mCase64;
    private ImageView mCase65;
    private ImageView mCase66;

    private ArrayList<ImageView> Cases6 = new ArrayList<ImageView>();
    public int c6=0;


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

    private TextView mPlayer1;
    private TextView mPlayer2;
    DataBase myDB;

    ////////

    //Sauvegarde pour changement d'activités
    public static final String BUNDLE_EXTRA_SCORE ="BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_EXTRA_NAME ="BUNDLE_EXTRA_NAME";

    //Sauvegarde pour instance
    public static final String BUNDLE_STATE_SCORE = "currentScore";

    private static final String BUNDLE_STATE_CASE1 = "LiseteColonne1" ;

    private  static  final String BUNDLE_STATE_C1 = "ValeurC1";


    private int mWinner =0;
    private int mMode;
    private User mUser1;
    private User mUser2;

    private boolean TourJoueur = true;

    public int Apparition(ArrayList<ImageView> c, int y,int x) {

        if (y < 6) {
            if (TourJoueur) {
                c.get(y).setColorFilter(Color.argb(255, 255, 0, 0));
                tab[y][x]=1;
                System.out.println("Rouge y:"+y+"  x:"+x);
                TourJoueur = false;

            } else {
                c.get(y).setColorFilter(Color.argb(255, 0, 0, 255));
                tab[y][x]=2;
                System.out.println("Bleu y:"+y+"  x:"+x);

                TourJoueur = true;
            }
            y++;
        }
        mWinner =Gagner(tab);
        if (mWinner !=0)
            FinJeu();

        return y;
    }


    public void AutoPlay() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();
                int rd=r.nextInt((7 - 1) + 1) + 1;
                System.out.println("Random: "+rd);

                switch (rd) {
                    case 1:
                        c1 = Apparition(Cases1, c1, 0);
                        break;
                    case 2:
                        c2 = Apparition(Cases2, c2, 1);
                        break;
                    case 3:
                        c3 = Apparition(Cases3, c3, 2);
                        break;
                    case 4:
                        c4 = Apparition(Cases4, c4, 3);
                        break;
                    case 5:
                        c5 = Apparition(Cases5, c5, 4);
                        break;
                    case 6:
                        c6 = Apparition(Cases6, c6, 5);
                        break;
                    case 7:
                        c7 = Apparition(Cases7, c7, 6);
                        break;
                }
            }
        }, 1000);

    }


    public int Gagner(int[][] table){
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                try {
                    if (table[i][j]!= 0){
                        // Vérification vertical
                        if (table[i][j] == table[i + 1][j] && table[i + 1][j] == table[i + 2][j] && table[i + 2][j] == table[i + 3][j])
                            return table[i][j];
                            //Vérification horizontal
                        else if (table[i][j] == table[i][j + 1] && table[i][j + 1] == table[i][j + 2] && table[i][j + 2] == table[i][j + 3])
                            return table[i][j];
                            //verification diagonale
                        else if (table[i][j] == table[i + 1][j + 1] && table[i + 1][j + 1] == table[i + 2][j + 2] && table[i + 2][j + 2] == table[i + 3][j + 3])
                            return table[i][j];
                            //verification diagonale
                        else if (table[i][j] == table[i - 1][j + 1] && table[i - 1][j + 1] == table[i - 2][j + 2] && table[i - 2][j + 2] == table[i - 3][j + 3])
                            return table[i][j];
                    }
                }
                catch(Exception e){
                    }
            }
        }
        return 0;
    }

    public int[][] tab =new int[6][7];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        Intent ActivData = getIntent();
        mMode = ActivData.getIntExtra("MODE",0);
        System.out.println(mMode);

        mPlayer1 = findViewById(R.id.Player1);
        mUser1 = new User(ActivData.getStringExtra("Username1"),ActivData.getIntExtra("Userscore1",0));
        System.out.println(mUser1.toString());

        mPlayer2 = findViewById(R.id.Player2);
        mUser2 = new User(ActivData.getStringExtra("Username2"),ActivData.getIntExtra("Userscore2",0));
        System.out.println(mUser2.toString());

        mPlayer1.setText(mUser1.getFirstname());

        mPlayer2.setText(mUser2.getFirstname());

        mCol1 = findViewById(R.id.button1);
        mCol2 = findViewById(R.id.button2);
        mCol3 = findViewById(R.id.button3);
        mCol4 = findViewById(R.id.button4);
        mCol5 = findViewById(R.id.button5);
        mCol6 = findViewById(R.id.button6);
        mCol7 = findViewById(R.id.button7);


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
                if (mMode==1)
                    c1=Apparition(Cases1,c1,0);
                if (mMode==2) {
                    c1 = Apparition(Cases1, c1, 0);
                    AutoPlay();
                }

            }
        });

        mCol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMode==1)
                    c2=Apparition(Cases2,c2,1);
                if (mMode==2) {
                    c2 = Apparition(Cases2, c2, 1);
                    AutoPlay();
                }
            }
        });

        mCol3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMode==1)
                    c3=Apparition(Cases3,c3,2);
                if (mMode==2) {
                    c3 = Apparition(Cases3, c3, 2);
                    AutoPlay();
                }
            }
        });

        mCol4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMode == 1)
                    c4 = Apparition(Cases4, c4, 3);
                if (mMode == 2) {
                    c4 = Apparition(Cases4, c4, 3);
                    AutoPlay();
                }
            }
        });

        mCol5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (mMode==1)
                        c5=Apparition(Cases5,c5,4);
                    if (mMode==2) {
                        c5 = Apparition(Cases5, c5, 4);
                        AutoPlay();
                    }
            }

        });

        mCol6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (mMode==1)
                        c6=Apparition(Cases6,c6,5);
                    if (mMode==2){
                        c6=Apparition(Cases6,c6,5);
                        AutoPlay();
                    }

        }
        });

        mCol7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (mMode==1)
                        c7=Apparition(Cases7,c7,6);
                    if (mMode==2) {
                        c7 = Apparition(Cases7, c7, 6);
                        AutoPlay();
                    }
            }

        });



        if (savedInstanceState != null) {
            mWinner = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            System.out.println("Score dans saved instance" +mWinner);

        } else {
            mWinner = 0;
            System.out.println("Score dans dans le else" +mWinner);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(BUNDLE_STATE_SCORE, mWinner);
        outState.putInt(BUNDLE_STATE_C1,c1);

        super.onSaveInstanceState(outState);
    }


    public void FinJeu(){
        //Boîte de dialogue
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Gagné")
                .setMessage("Le Joueur: " + mWinner+"  a Gagné!!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Fin de l'activité
                        Intent intent =new Intent();
                        if(mWinner==1){
                            intent.putExtra(BUNDLE_EXTRA_SCORE, mUser1.getScore()+1);
                            intent.putExtra(BUNDLE_EXTRA_NAME, mUser1.getFirstname());
                        }
                        else if(mWinner==2){
                            intent.putExtra(BUNDLE_EXTRA_SCORE, mUser2.getScore()+1);
                            intent.putExtra(BUNDLE_EXTRA_NAME, mUser2.getFirstname());
                        }
                        else
                            intent.putExtra(BUNDLE_EXTRA_SCORE, mWinner);

                        setResult(RESULT_OK,intent);
                        finish();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        //Fin de l'activité (Cas ou l'user ferme le dialogue sans passer par "ok"
                        Intent intent =new Intent();

                        intent.putExtra(BUNDLE_EXTRA_SCORE, mWinner);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                })
                .create()
                .show();
    }
}


