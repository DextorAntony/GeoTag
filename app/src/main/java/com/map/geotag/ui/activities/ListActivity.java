package com.map.geotag.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.map.geotag.R;
import com.map.geotag.ui.adapters.LocationListAdapter;
import com.map.geotag.database.dao.LocationDAO;
import com.map.geotag.model.Location;

import java.util.ArrayList;
import java.util.Objects;

public class ListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private TextView tvnoLocation;
    private ArrayList<Location> locations;
    private LocationDAO locationDAO;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationlist);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if (getIntent() != null && getIntent().getExtras() != null) {
            locations = (ArrayList<Location>) getIntent().getSerializableExtra("locations");
        }

        recyclerView = findViewById(R.id.rvViewlocations);
        swipeRefreshLayout = findViewById(R.id.srlViewtour);
        swipeRefreshLayout.setOnRefreshListener(this);
        tvnoLocation = findViewById(R.id.tvnoLocation);
        locationDAO = new LocationDAO(getApplicationContext());
        setUpView();


    }

    public void setUpView() {
        if (locations == null) {
            locations = new ArrayList<>();
            locationDAO = new LocationDAO(getApplicationContext());
            locations = locationDAO.getLocations();
        }
        setUpRecyclerView();
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
    }

    private void setUpRecyclerView() {
        if (locations == null || locations.size() == 0) {
            tvnoLocation.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvnoLocation.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            LocationListAdapter locationListAdapter = new LocationListAdapter(locations, this);
            recyclerView.setAdapter(locationListAdapter);
        }
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setUpView();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 500);
    }
}
