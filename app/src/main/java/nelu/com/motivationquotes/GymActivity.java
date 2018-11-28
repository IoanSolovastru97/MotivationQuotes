package nelu.com.motivationquotes;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.List;

import nelu.com.motivationquotes.utilis.ImageProcessesing;

public class GymActivity extends MainActivity {
    private RecyclerView recyclerView;
    private List<String> images;

    private DrawerLayout gymDrawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_quotes);


        gymDrawerLayout = findViewById(R.id.gym_drawer_layout);
        toggle = new ActionBarDrawerToggle(this, gymDrawerLayout, R.string.open, R.string.close);
        gymDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.quotesGym);

        recyclerView = findViewById(R.id.gym_recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        images = ImageProcessesing.getListImage(GymActivity.this,recyclerView,"QuotesGym");
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.quotesFun:
                startActivity(new Intent(GymActivity.this, SadActivity.class));
                break;
            case R.id.quotes_load_images:
                startActivity(new Intent(GymActivity.this, LoadImages.class));
                break;
            case R.id.about_us:
                startActivity(new Intent(GymActivity.this, AboutUsActivity.class));
                break;
            case R.id.rate_us:
                super.rateUs();
                break;
        }
        gymDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
