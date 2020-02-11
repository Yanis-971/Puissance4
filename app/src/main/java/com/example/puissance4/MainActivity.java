package com.example.puissance4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.User;

public class MainActivity extends AppCompatActivity {

    private TextView mBienvenue;
    private TextInputEditText mPseudo;
    private TextInputEditText mPseudo2;
    private MaterialButton mJouer;
    private MaterialButton mClassement;
    private boolean V2ok = false;

    private User mUser;
    private User mUser2;
    private SharedPreferences mPreferences;
    public static final int GAME_ACTIVITY_REQUEST_CODE = 2;

    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";  //Dernier score
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME"; //Dernier pseudo

    ///////////////////
    DataBase myDB;
    ///////////////////////////


    //Au résultat du Game
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            int WinnerScore = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE,0);
            String WinnerName = data.getStringExtra(GameActivity.BUNDLE_EXTRA_NAME);
            System.out.println("Winner = "+WinnerName+WinnerScore);


            User mUsersave = new User(WinnerName,WinnerScore);
            boolean test = myDB.VerifUser(mUsersave);
            System.out.println(test);

            //Vérification présence User
            if (test)
               myDB.updateData(WinnerName,WinnerScore);

            else
                myDB.insertData(WinnerName,WinnerScore);


            SpannableStringBuilder spannable;
            //Coloration du message du résultat de l'actvité Game_Activity
            if(mUsersave.getFirstname().equals(mPreferences.getString(PREF_KEY_FIRSTNAME,null)))
                spannable = new SpannableStringBuilder("Dernier Match :  a Gagné");
            else
                spannable = new SpannableStringBuilder("Dernier Match :  a Perdu");

            spannable.setSpan(
                    new ForegroundColorSpan(Color.parseColor("#FFA000")),
                    15, // start
                    17, // end
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            );
            spannable.setSpan(
                    new StyleSpan(Typeface.BOLD),
                    15, // start
                    17, // end
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            );

            spannable.insert(16, mPreferences.getString(PREF_KEY_FIRSTNAME,null));

            mClassement.setEnabled(true);

            mBienvenue.setText(spannable);
            mPseudo.setText(mPreferences.getString(PREF_KEY_FIRSTNAME,null));

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DataBase(this);

        mBienvenue = findViewById(R.id.activity_main_bievenue_txt);
        mPseudo =  findViewById(R.id.TestInput);
        mJouer = findViewById(R.id.activity_main_jouer_btn);
        mClassement = findViewById(R.id.activity_main_classement_btn);
        mUser =new User();
        mUser2=new User();



        mJouer.setEnabled(false);

        mPreferences= getSharedPreferences("Préféré",MODE_PRIVATE);

        if(mPreferences.getString(PREF_KEY_FIRSTNAME,null) != null){

            //Colorer du texte

            SpannableStringBuilder spannable = new SpannableStringBuilder("A la dernière partie  a Joué.");
            spannable.setSpan(
                    new ForegroundColorSpan(Color.parseColor("#FFA000")),
                    20, // start
                    22, // end
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            );
            spannable.setSpan(
                    new StyleSpan(Typeface.BOLD),
                    20, // start
                    22, // end
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            );
            spannable.insert(21, mPreferences.getString(PREF_KEY_FIRSTNAME,null));

            mUser.setFirstname(mPreferences.getString(PREF_KEY_FIRSTNAME,null));
            mUser.setScore(mPreferences.getInt(PREF_KEY_SCORE,0));

            mBienvenue.setText(spannable);
            mPseudo.setText(mUser.getFirstname());
            mJouer.setEnabled(true);
        }

        Cursor res = myDB.getAllData();
        if (res.getCount() == 0){
            mClassement.setEnabled(false);
        }


        //Listener pour observer les changements de textes
        mPseudo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mJouer.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUser.setFirstname(mPseudo.getText().toString());
                mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstname()).apply();
                mUser.setScore(0);

                boolean verif = myDB.VerifUser(mUser);
                if(verif==true)
                    mUser = myDB.getUser(mUser.getFirstname());
                mPreferences.edit().putInt(PREF_KEY_SCORE, mUser.getScore()).apply();

                ChoixOptions();


            }
        });

        mClassement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent classActivityIntent = new Intent (MainActivity.this, ClassementActivity.class);
                startActivity(classActivityIntent);

            }
        });


    }

    public void ChoixOptions(){
        final AlertDialog.Builder builder = new MaterialAlertDialogBuilder(this);
        View mView = getLayoutInflater().inflate(R.layout.activity_dialog,null);
        TextView mTitle = mView.findViewById(R.id.Title);
        MaterialButton mVersus = mView.findViewById((R.id.Versus));
        MaterialButton mSolo = mView.findViewById((R.id.Solo));
        final TextInputLayout mContain = mView.findViewById(R.id.Username22);
        mPseudo2 = mView.findViewById(R.id.Username2);
        mPseudo2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length()!=0)
                    V2ok = true;
                else
                    V2ok=false;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        builder.setView(mView);
        final AlertDialog mOptions = builder.create();
        mOptions.show();


        mVersus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(V2ok);
                if (V2ok){
                    //Changement d'activité
                    mUser2.setFirstname(mPseudo2.getText().toString());
                    boolean verif = myDB.VerifUser(mUser2);
                    if (verif)
                        mUser2=myDB.getUser(mUser2.getFirstname());
                    Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                    gameActivity.putExtra("Username1",mUser.getFirstname());
                    gameActivity.putExtra("Userscore1",mUser.getScore());
                    gameActivity.putExtra("Username2",mUser2.getFirstname());
                    gameActivity.putExtra("Userscore2",mUser2.getScore());
                    gameActivity.putExtra("MODE",1);
                    System.out.println(mPseudo.getText()+"gettext()  "+mPseudo2.getText().toString()+"tostring()");
                    startActivityForResult(gameActivity,GAME_ACTIVITY_REQUEST_CODE);
                    mOptions.dismiss();
                    V2ok=false;


                }
                else {
                    mContain.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Enter 2nd Username", Toast.LENGTH_SHORT).show();

                }

            }
        });

        mSolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                gameActivity.putExtra("Username1",mUser.getFirstname());
                gameActivity.putExtra("Userscore1",mUser.getScore());
                gameActivity.putExtra("Username2","COM");
                gameActivity.putExtra("MODE",2);
                startActivityForResult(gameActivity,GAME_ACTIVITY_REQUEST_CODE);
                mOptions.dismiss();
                V2ok=false;
            }
        });
                mOptions.show();
    }


}
