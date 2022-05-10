package com.example.persistence;

public class DatabaseTables {

    static class Cars {

        static final String TABLE_NAME = "car";
        static final String COLUMN_NAME_ID = "id";
        static final String COLUMN_NAME_NAME = "name";
        static final String COLUMN_NAME_TOP_SPEED = "top speed";

    }

    static final String SQL_CREATE_CARS =
            // Här skapar vi vår tabell med tre kolumner. Typ såhär:
            // "CREATE TABLE car (id INTEGER PRIMARY KEY, name TEXT, top speed INT)"
            "CREATE TABLE " + Cars.TABLE_NAME + " (" +
                    Cars.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    Cars.COLUMN_NAME_NAME + " TEXT," +
                    Cars.COLUMN_NAME_TOP_SPEED + " INT)";

    static final String SQL_DELETE_CARS =
            // "DROP TABLE IF EXISTS car"
            "DROP TABLE IF EXISTS " + Cars.TABLE_NAME;
}