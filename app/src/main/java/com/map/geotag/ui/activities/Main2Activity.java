package com.map.geotag.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.map.geotag.R;
import com.map.geotag.ui.adapters.LocationListAdapter;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void main(View view) {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    public void listv(View view) {
        Intent i = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(i);
    }
}
