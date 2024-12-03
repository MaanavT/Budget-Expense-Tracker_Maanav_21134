package com.project.expensetracker_mysspending.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "ExpenseTracker.db";

    static final String USERNAME = "username";

    static final String USER_TABLE_NAME = "User";
    static final String USER_PASSWORD = "password";
    static final String USER_FNAME = "fname";
    static final String USER_LNAME = "lname";
    static final String USER_EMAIL = "email";
    static final String USER_AGE = "age";




    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table User " +
                "(username text primary key check(length(username)<=60)," +
                "password text check(length(password)<=60)," +
                "fname text check(length(fname)<=60)," +
                "lname text check(length(lname)<=60)," +
                "email text check(length(email)<=60)," +
                "age integer check(age > 0 and age < 100)," +
                "income numeric default 0," +
                "savings numeric default 0," +
                "allowance numeric default 0)"
        );

        db.execSQL("create table Transactions " +
                "(username text," +
                "day integer, " +
                "month integer, " +
                "year integer, " +
                "hour integer, " +
                "minute integer, " +
                "type text, " +
                "amount numeric," +
                "foreign key(username) references User(username))"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS Transactions");
        db.execSQL("DROP TABLE IF EXISTS USER");
        onCreate(db);
    }

    /**
     * Inserts transaction
     */


    /**
     * Inserts user
     */
    public boolean insertUser(
            String username, String password, String fname, String lname, String email,
            int age)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(USER_PASSWORD, password);
        contentValues.put(USER_FNAME, fname);
        contentValues.put(USER_LNAME, lname);
        contentValues.put(USER_EMAIL, email);
        contentValues.put(USER_AGE, age);


        db.insert(USER_TABLE_NAME, null, contentValues);

        return true;

    }
    /**
     * Returns true if user exists
     * @return
     */
    public boolean checkUser(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from User " +
                        "where username= '" + username +"'",
                null);

        return res.moveToFirst();

    }

    /**
     * Returns true if user exists
     * @return
     */
    public boolean getUser(String username, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from User " +
                "where username= '" + username + "' and password= '" + password + "'",
                null);

        return res.moveToFirst();

    }

    /**
     * Returns user allowance
     */


    /**
     * Updates user password
     */
    public void updatePassword(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_PASSWORD, password);

        db.update(USER_TABLE_NAME, contentValues, "username=?", new String[]{username});

    }

    /**
     * Updates user profile
     *
     */
    public void updateProfile(String username,
            String fname, String lname, String email, int age)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_FNAME, fname);
        contentValues.put(USER_LNAME, lname);
        contentValues.put(USER_EMAIL, email);
        contentValues.put(USER_AGE, age);

        db.update(USER_TABLE_NAME, contentValues, "username=?", new String[]{username});

    }
}