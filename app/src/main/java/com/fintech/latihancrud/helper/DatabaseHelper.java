package com.fintech.latihancrud.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "crud";
    SQLiteDatabase db = this.getWritableDatabase();

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // membuat table
        final String CREATE_TABLE = "create table users (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,email TEXT NOT NULL)";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String,String>> getAll(){
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        String QUERY = "select * from users";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY,null);

        if (cursor.moveToFirst()){
            do{
                HashMap<String,String> map = new HashMap<>();
                map.put("id",cursor.getString(0));
                map.put("name",cursor.getString(1));
                map.put("email",cursor.getString(2));
                list.add(map);


            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public void insertData(String name, String email){
        String query = "insert into users (name, email) values ('"+name+"','"+email+"')";
        db.execSQL(query);
    }
    public void updateData(int id,String name, String email){
        String query = "update users set name= '"+name+"', email='"+email+"' where id= "+id;
        db.execSQL(query);
    }
    public void deleteData(int id){
        String query = "delete from users where id = "+id;
        db.execSQL(query);
    }
}