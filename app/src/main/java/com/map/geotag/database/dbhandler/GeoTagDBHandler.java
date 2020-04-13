package com.map.geotag.database.dbhandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.map.geotag.model.Location;

public class GeoTagDBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "GeoTag.db";

    private static final String CREATE_LOCATION_TABLE = "CREATE TABLE IF NOT EXISTS " + Location.TABLE_NAME + "("
            + Location.KEY_ID + " INTEGER PRIMARY KEY, "
            + Location.KEY_ADDRESS+ " TEXT, "
            + Location.KEY_LAT+ " TEXT, "
            + Location.KEY_LONG+ " TEXT, "
            + Location.KEY_FILE+ " TEXT "
            + ")";

    public GeoTagDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOCATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
