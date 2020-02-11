package com.example.puissance4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.User;

public class ClassementActivity extends AppCompatActivity {

    private ListView mClassement;
    private Button mNumerique;
    private Button mAlphabetique;
    public static final String BEST_SCORE = "BEST_SCORE";
    public static final String BEST_FIRSTNAME = "BEST_FIRSTNAME";
    private ArrayList<SharedPreferences> mListe= new ArrayList<>();
    //
    ArrayList<User> mClass= new ArrayList<>();
    //
    private SharedPreferences mPreferences;
    public static final int MAIN_ACTIVITY_REQUEST_CODE = 1;
    private User mU1;
    private  User mU2;
    private  User mU3;
    private  User mU4;
    private  User mU5;
    DataBase myDB;


    Comparator<User> compareFirstname = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o1.getFirstname().compareToIgnoreCase(o2.getFirstname());
        }
    };

    Comparator<User> compareScore = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o1.getScore()-o2.getScore();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);
        myDB = new DataBase(this);

        mClassement = (ListView) findViewById(R.id.classement_main_classement_list);
        mNumerique = (Button) findViewById(R.id.classement_main_numerique_btn);
        mAlphabetique = (Button)findViewById(R.id.classement_main_alphabetique_btn);

        mPreferences = getSharedPreferences("Préféré",MODE_PRIVATE);
        //mListe= data.getIntExtra(MainActivity.BUNDLE_BEST_SCORE,0);


        //Cursor res = myDB.getAllData();
         mClass=myDB.ListUser();


        /*
        final ArrayList<User> arrayList = new ArrayList<>();

        mU1 = new User(mPreferences.getString("PREMIER_PSEUDO",null),mPreferences.getInt("PREMIER_SCORE",0));
        mU2 = new User(mPreferences.getString("DEUXIEME_PSEUDO",null),mPreferences.getInt("DEUXIEME_SCORE",0));
        mU3 = new User(mPreferences.getString("TROISIEME_PSEUDO",null),mPreferences.getInt("TROISIEME_SCORE",0));
        mU4 = new User(mPreferences.getString("QUATRIEME_PSEUDO",null),mPreferences.getInt("QUATRIEME_SCORE",0));
        mU5 = new User(mPreferences.getString("CINQUIEME_PSEUDO",null),mPreferences.getInt("CINQUIEME_SCORE",0));

        if (mU1.getFirstname() !=null){
            arrayList.add(mU1);
        }
        if (mU2.getFirstname() !=null){
            arrayList.add(mU2);
        }
        if (mU3.getFirstname() !=null){
            arrayList.add(mU3);
        }
        if (mU4.getFirstname() !=null){
            arrayList.add(mU4);
        }
        if (mU5.getFirstname() !=null){
            arrayList.add(mU5);
        }
       */

        final ArrayAdapter arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,mClass);

        mClassement.setAdapter(arrayAdapter);

        mAlphabetique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(mClass, compareFirstname);
                mClassement.setAdapter(arrayAdapter);
            }
        });

        mNumerique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(mClass, compareScore);
                Collections.reverse(mClass);
                mClassement.setAdapter(arrayAdapter);
            }
        });
    }
}
