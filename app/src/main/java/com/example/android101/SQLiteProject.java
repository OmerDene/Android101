package com.example.android101;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class SQLiteProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_project);
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(name VARCHAR, age INTEGER)");
           // database.execSQL("INSERT INTO musicians(name ,age ) VALUES ('James',50)");
            database.execSQL("INSERT INTO musicians(name ,age ) VALUES ('omer',20)");
            Cursor cursor = database.rawQuery("SELECT * FROM musicians ",null);
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            while (cursor.moveToNext()) {
                System.out.println("Name: " +cursor.getString(nameIx));
                System.out.println("Age: " +cursor.getInt(ageIx));

            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

}
