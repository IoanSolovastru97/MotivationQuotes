package nelu.com.motivationquotes;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import nelu.com.motivationquotes.utilis.ImageProcessesing;
import nelu.com.motivationquotes.utilis.MyAdapter;

public class GymActivity extends MainActivity {
    private static final String TAG = "GymActivity";
    private RecyclerView recyclerView;
    private List<String> images;
    private  MyAdapter adapter;

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
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        images = ImageProcessesing.getListImage(GymActivity.this, recyclerView, "quotes1");
        adapter = new MyAdapter(images, GymActivity.this);
        recyclerView.setAdapter(adapter);



        /*
                Toast.makeText(GymActivity.this,"Hello",Toast.LENGTH_SHORT);
                Intent intent = new Intent(GymActivity.this, ZoomImage.class);
                startActivity(intent);
         */

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
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
