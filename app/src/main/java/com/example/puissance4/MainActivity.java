package com.example.puissance4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.User;

public class MainActivity extends AppCompatActivity {

    private TextView mBienvenue;
    private EditText mPseudo;
    private Button mJouer;
    private Button mClassement;

    private User mUser;
    private SharedPreferences mPreferences;
    public static final int GAME_ACTIVITY_REQUEST_CODE = 2;

    private ArrayList<User> mMeilleurs = new ArrayList<>();

    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";  //Dernier score
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME"; //Dernier pseudo

    public static final String BUNDLE_BEST_SCORE ="BUNDLE_BEST_SCORE";
    public static final String BUNDLE_BEST_FIRSTNAME = "BUNDLE_BEST_FIRSTNAME";

    private  User mU1;
    private  User mU2;
    private  User mU3;
    private  User mU4;
    private  User mU5;

    public static final String PREMIER_SCORE ="PREMIER_SCORE";
    public static final String DEUXIEME_SCORE ="DEUXIEME_SCORE";
    public static final String TROISIEME_SCORE ="TROISIEME_SCORE";
    public static final String QUATRIEME_SCORE ="QUATRIEME_SCORE";
    public static final String CINQUIEME_SCORE ="CINQUIEME_SCORE";

    public static final String PREMIER_PSEUDO ="PREMIER_PSEUDO";
    public static final String DEUXIEME_PSEUDO ="DEUXIEME_PSEUDO";
    public static final String TROISIEME_PSEUDO ="TROISIEME_PSEUDO";
    public static final String QUATRIEME_PSEUDO ="QUATRIEME_PSEUDO";
    public static final String CINQUIEME_PSEUDO ="CINQUIEME_PSEUDO";

    Comparator<User> compareFirstname = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o1.getFirstname().compareTo(o2.getFirstname());
        }
    };

    Comparator<User> compareScore = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o1.getScore()-o2.getScore();
        }
    };



    //Au résultat du Game
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            int score = mPreferences.getInt(PREF_KEY_SCORE,0) + data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE,0);

            mClassement.setEnabled(true);

            mPreferences.edit().putInt(PREF_KEY_SCORE,score).apply();

            //Coloration du message du résultat de l'actvité Game_Activity
            SpannableStringBuilder spannable = new SpannableStringBuilder("Dernier Match :  a     ");
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
            spannable.setSpan(
                    new ForegroundColorSpan(Color.parseColor("#FFA000")),
                    spannable.toString().indexOf("a")+1, // start
                    spannable.toString().indexOf("a")+3, // end
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            );
            spannable.insert(16, mPreferences.getString(PREF_KEY_FIRSTNAME,null));
            spannable.insert(17, Integer.toString(mPreferences.getInt(PREF_KEY_SCORE,0)));

            mBienvenue.setText(spannable);
            mPseudo.setText(mPreferences.getString(PREF_KEY_FIRSTNAME,null));
            mUser.setScore(score);

            User mUsersave = new User(mPreferences.getString(PREF_KEY_FIRSTNAME,null),mPreferences.getInt(PREF_KEY_SCORE,0));
            System.out.println(mUsersave.toString());
            mMeilleurs.add(mUsersave);


           Collections.sort(mMeilleurs,compareScore);
           Collections.reverse(mMeilleurs);


            mPreferences.edit().putInt(PREMIER_SCORE,mMeilleurs.get(0).getScore()).apply();
            mPreferences.edit().putInt(DEUXIEME_SCORE,mMeilleurs.get(1).getScore()).apply();
            mPreferences.edit().putInt(TROISIEME_SCORE,mMeilleurs.get(2).getScore()).apply();
            mPreferences.edit().putInt(QUATRIEME_SCORE,mMeilleurs.get(3).getScore()).apply();
            mPreferences.edit().putInt(CINQUIEME_SCORE,mMeilleurs.get(4).getScore()).apply();

            mPreferences.edit().putString(PREMIER_PSEUDO,mMeilleurs.get(0).getFirstname()).apply();
            mPreferences.edit().putString(DEUXIEME_PSEUDO,mMeilleurs.get(1).getFirstname()).apply();
            mPreferences.edit().putString(TROISIEME_PSEUDO,mMeilleurs.get(2).getFirstname()).apply();
            mPreferences.edit().putString(QUATRIEME_PSEUDO,mMeilleurs.get(3).getFirstname()).apply();
            mPreferences.edit().putString(CINQUIEME_PSEUDO,mMeilleurs.get(4).getFirstname()).apply();

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBienvenue = (TextView) findViewById(R.id.activity_main_bievenue_txt);
        mPseudo = (EditText) findViewById(R.id.activity_main_pseudo_input);
        mJouer = (Button) findViewById(R.id.activity_main_jouer_btn);
        mClassement = (Button) findViewById(R.id.activity_main_classement_btn);
        mUser =new User();


        mJouer.setEnabled(false);

        mPreferences= getSharedPreferences("Préféré",MODE_PRIVATE);

        mU1 = new User(mPreferences.getString(PREMIER_PSEUDO,null),mPreferences.getInt(PREMIER_SCORE,0));
        mU2 = new User(mPreferences.getString(DEUXIEME_PSEUDO,null),mPreferences.getInt(DEUXIEME_SCORE,0));
        mU3 = new User(mPreferences.getString(TROISIEME_PSEUDO,null),mPreferences.getInt(TROISIEME_SCORE,0));
        mU4 = new User(mPreferences.getString(QUATRIEME_PSEUDO,null),mPreferences.getInt(QUATRIEME_SCORE,0));
        mU5 = new User(mPreferences.getString(CINQUIEME_PSEUDO,null),mPreferences.getInt(CINQUIEME_SCORE,0));

        mMeilleurs.add(mU1);
        mMeilleurs.add(mU2);
        mMeilleurs.add(mU3);
        mMeilleurs.add(mU4);
        mMeilleurs.add(mU5);


        System.out.println(mMeilleurs);

        if(mPreferences.getString(PREF_KEY_FIRSTNAME,null) != null){

            //Colorer du texte

            SpannableStringBuilder spannable = new SpannableStringBuilder("A la dernière partie  a Jouer.");
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
            spannable.setSpan(
                    new ForegroundColorSpan(Color.parseColor("#FFA000")),
                    20, // start
                    23, // end
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            );
            spannable.insert(21, mPreferences.getString(PREF_KEY_FIRSTNAME,null));


            mBienvenue.setText(spannable);
            mPseudo.setText(mPreferences.getString(PREF_KEY_FIRSTNAME,null));
            mJouer.setEnabled(true);
        }

        if(mMeilleurs.get(0).getFirstname()== null){
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

                //Changement d'activité
                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivity,GAME_ACTIVITY_REQUEST_CODE);
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


}
