package com.example.elessar1992.myeventprofile.sql;

/**
 * Created by elessar1992 on 1/22/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListAdapter;

import com.example.elessar1992.myeventprofile.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elessar1992 on 1/17/18.
 */

public class DataBaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "match.db";
    public static final String TABLE_NAME = "match";
    public static final String COL_id = "ID";
    public static final String COL_firstName = "FirstName";
    public static final String COL_lastName = "LastName";
    public static final String COL_userName = "UserName";
    public static final String COL_email = "Email";
    public static final String COL_password = "Password";

    private String CREATE_USER_TABLE2 = "CREATE TABLE " + TABLE_NAME + "("
            + COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_firstName + " TEXT," + COL_lastName + " TEXT," + COL_userName + " TEXT,"
            + COL_email + " TEXT," + COL_password + " TEXT" + ")";




    public DataBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,FirstName TEXT,LastName TEXT,UserName TEXT,Email TEXT,Password TEXT");
        db.execSQL(CREATE_USER_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_firstName, user.getFirstname());
        values.put(COL_lastName, user.getLastname());
        values.put(COL_userName, user.getUsername());
        values.put(COL_email, user.getEmail());
        values.put(COL_password, user.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public Cursor getallData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    // just getting the id of the data
    public Cursor getItemID(String firstName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_id + " FROM " + TABLE_NAME +
                " WHERE " + COL_userName + " = '" + firstName + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public List<User> getAllUser()
    {
        String[] columns = {
                COL_id,
                COL_email,
                COL_userName,
                COL_firstName,
                COL_lastName,
                COL_password
        };

        String sortOrder = COL_firstName + " ASC";
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, sortOrder);

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst())

        {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_id))));
                user.setUsername(cursor.getString(cursor.getColumnIndex(COL_userName)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COL_email)));
                user.setFirstname(cursor.getString(cursor.getColumnIndex(COL_firstName)));
                user.setLastname(cursor.getString(cursor.getColumnIndex(COL_lastName)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COL_password)));
                userList.add(user);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }


    /*public boolean insertdata(String firstName, String lastName,String myemail, String mypassword)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, firstName);
        contentValues.put(COL_3, lastName);
        contentValues.put(COL_4, myemail);
        contentValues.put(COL_5, mypassword);

        long id = db.insert(TABLE_NAME, null, contentValues);
        if (id == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }*/

    public void insertdata(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_firstName, user.getFirstname());
        contentValues.put(COL_lastName, user.getLastname());
        contentValues.put(COL_userName, user.getUsername());
        contentValues.put(COL_email, user.getLastname());
        contentValues.put(COL_password, user.getPassword());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();

    }



    public boolean checkUser(String email, String password)
    {
        String [] columns = {COL_id};
        SQLiteDatabase db = this.getReadableDatabase();
        String myQuery =  COL_email + " = ?" + " AND " + COL_password + " = ?";
        String [] argumentSelection = {email, password};
        Cursor cursor = db.query(TABLE_NAME,columns,myQuery,argumentSelection,null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount > 0)
        {
            return true;
        }
        return false;



    }

    /*public boolean updatedata(String id, String firstName, String lastName, String mypassword, String myemail, String myphone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, id);
        contentValues.put(COL_2, firstName);
        contentValues.put(COL_3, lastName);
        contentValues.put(COL_4, mypassword);
        contentValues.put(COL_5, myemail);
        contentValues.put(COL_6, myphone);
        db.update(TABLE_NAME, contentValues, "id =?",new String[]{id});
        return true;
    }*/

    public void updateName(String newName, int id, String oldName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL_userName+
                " = '" + newName + "' WHERE " + COL_id + " = '" + id + "'" +
                " AND " + COL_userName + " = '" + oldName + "'";

        db.execSQL(query);
    }

    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL_id + " = '" + id + "'" +
                " AND " + COL_userName + " = '" + name + "'";

        db.execSQL(query);
    }
}