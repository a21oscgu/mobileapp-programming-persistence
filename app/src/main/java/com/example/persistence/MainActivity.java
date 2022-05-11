package com.example.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

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
        Button write = findViewById(R.id.write);
        Button read = findViewById(R.id.read);
        Button delete = findViewById(R.id.delete);

        final String name = findViewById(R.id.edit_text_name).toString();
        final String color = findViewById(R.id.edit_text_color).toString();

        EditText editTextTopSpeed = findViewById(R.id.edit_text_top_speed);
        String string_from_et = editTextTopSpeed.getText().toString();
        final int topSpeed = Integer.parseInt(string_from_et);

        //When the user taps (clicks) the 'Write' button the values in the EditText views
        // should be written as a single row to a SQLite database table
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("logcat_output","WRITE");
                addCar(name, color,topSpeed);
            }
        });

        //When the user taps the 'Read' button, all rows should be read from the database and displayed in the TextView.
        // Hint: Use '\n' to start a new line
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("logcat_output","READ");
                printCars();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("logcat_output","READ");
                /*for(int i=1; i <= ; i++){
                    deleteCar(i);
                }*/
            }
        });
    }
    private void printCars(){
        Cursor cursor = database.query(DatabaseTables.Cars.TABLE_NAME, null, null, null, null, null, null);
        List<Car> carsList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Car car = new Car(
                    cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseTables.Cars.COLUMN_NAME_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Cars.COLUMN_NAME_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Cars.COLUMN_NAME_COLOR)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseTables.Cars.COLUMN_NAME_TOPSPEED))
            );
            carsList.add(car);
            Log.d("logcat_output", "Added car, to carsList, with id: " + car.getId() + ", name: " + car.getName() + ", top speed: " + car.getTopSpeed() + ".");
        }
        cursor.close();
    }

    private void addCar(String name, String color, int topSpeed){
        ContentValues values = new ContentValues();
        values.put(DatabaseTables.Cars.COLUMN_NAME_NAME, name);
        values.put(DatabaseTables.Cars.COLUMN_NAME_COLOR, color);
        values.put(DatabaseTables.Cars.COLUMN_NAME_TOPSPEED, topSpeed);
        long id = database.insert(DatabaseTables.Cars.TABLE_NAME,null,values);
        Log.d("logcat_output", "Created car with id: " + id + ", name: " + name + ", color: " + color + ", top speed: " + topSpeed + ".");
    }

    private int deleteCar(long id) {
        String selection = DatabaseTables.Cars.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        return database.delete(DatabaseTables.Cars.TABLE_NAME, selection, selectionArgs);
    }
}