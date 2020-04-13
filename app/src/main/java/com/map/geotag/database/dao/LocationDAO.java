package com.map.geotag.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.map.geotag.database.dbhandler.GeoTagDBHandler;
import com.map.geotag.model.Location;

import java.util.ArrayList;

public class LocationDAO {


    private final GeoTagDBHandler geoTagHandler;
    private Context ctx;
    private SQLiteDatabase db;

    public LocationDAO(Context ctx) {
        this.ctx = ctx;
        geoTagHandler = new GeoTagDBHandler(ctx);
    }

    public boolean insert(Location location) {
        db = geoTagHandler.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Location.KEY_ID, location.getId());
        contentValues.put(Location.KEY_ADDRESS, location.getAddress());
        contentValues.put(Location.KEY_LAT, location.getLat());
        contentValues.put(Location.KEY_LONG, location.getLongi());
        contentValues.put(Location.KEY_FILE, location.getFile());
        try {
            db.enableWriteAheadLogging();
            if(db.insert(Location.TABLE_NAME, null, contentValues)!=-1) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        try {
            if(geoTagHandler!=null) {
                db = geoTagHandler.getReadableDatabase();
                db.enableWriteAheadLogging();
                String selectQuery;
                selectQuery = "SELECT * FROM " + Location.TABLE_NAME;
                Cursor cursor = db.rawQuery(selectQuery, null);

                if (cursor.moveToFirst()) {
                    do {
                        Location location = new Location();
                        location.setId(cursor.getInt(cursor.getColumnIndex(Location.KEY_ID)) + "");
                        location.setAddress(cursor.getString(cursor.getColumnIndex(Location.KEY_FILE)) + "");
                        location.setLat(cursor.getString(cursor.getColumnIndex(Location.KEY_LAT)));
                        location.setLongi(cursor.getString(cursor.getColumnIndex(Location.KEY_LONG)));
                        location.setAddress(cursor.getString(cursor.getColumnIndex(Location.KEY_ADDRESS)));
                        location.setFile(cursor.getString(cursor.getColumnIndex(Location.KEY_FILE)));
                        locations.add(location);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
        return locations;
    }

}
