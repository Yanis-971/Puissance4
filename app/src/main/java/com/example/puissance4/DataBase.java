package com.example.puissance4;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;
import java.util.ArrayList;

import model.User;

public class DataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Classement";
    public static final String TABLE_NAME = "Joueur";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "SCORE";

    public DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, SCORE INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String username,int score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,score);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from "+TABLE_NAME,null);
        return res;
    }

    public ArrayList<User> ListUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<User> Liste = new ArrayList<>();

        Cursor res = db.rawQuery("Select * from "+TABLE_NAME,null);
        if (res!= null){

            //StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                User mUser = new User();
                mUser.setFirstname(res.getString(res.getColumnIndex(COL_2)));
                mUser.setScore(res.getInt(res.getColumnIndex(COL_3)));
                Liste.add(mUser);
            }


        }
        return Liste;
    }

    public User getUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        User mUser = new User();
        mUser.setFirstname(username);

        Cursor res = db.rawQuery("Select * from "+TABLE_NAME+" where USERNAME = '"+ username +"'",null);
        if (res!= null){
            System.out.println("lol"+res);
            while (res.moveToNext()) {
                System.out.println(res.getString(res.getColumnIndex(COL_2)));
                mUser.setFirstname(res.getString(res.getColumnIndex(COL_2)));
                mUser.setScore(res.getInt(res.getColumnIndex(COL_3)));
            }
        }
        return mUser;
    }

    public boolean VerifUser(User u){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("Select * from "+TABLE_NAME+" where USERNAME = '"+ u.getFirstname() +"'",null);
        if (res.getCount() != 0)
            return true;
        else
            return false;
    }

    public boolean updateData(String username,int score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,score);
        db.update(TABLE_NAME,contentValues,"USERNAME = ?",new String[] {username});
        return true;

    }

}
