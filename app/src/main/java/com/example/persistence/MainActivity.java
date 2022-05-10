package com.example.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Skapar ett objekt
        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();

        // Kommentarer:
        // Lägg addCar under en "button-clicklistener istället"
        // Och skapa t.ex. en annan knapp som tar bort berg. Name och Height kan också ha två edittexts.
        addCar("Red", 300);
        printCars();
    }
    private void printCars(){
        Cursor cursor = database.query(DatabaseTables.Cars.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Car car = new Car(
                    cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseTables.Cars.COLUMN_NAME_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Cars.COLUMN_NAME_NAME)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseTables.Cars.COLUMN_NAME_TOPSPEED))
            );
            Log.d("MainActivity", car.getId() + " " + car.getTopSpeed());
        }
        cursor.close();
    }

    private void addCar(String name, int topSpeed){
        ContentValues values = new ContentValues();
        values.put(DatabaseTables.Cars.COLUMN_NAME_NAME, name);
        values.put(DatabaseTables.Cars.COLUMN_NAME_TOPSPEED, topSpeed);
        long id = database.insert(DatabaseTables.Cars.TABLE_NAME,null,values);
        Log.d("MainActivity", "Created car with id: " + id);
    }
}