package nelu.com.motivationquotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

public class AboutUsActivity extends MainActivity {
    private DrawerLayout aboutUsDrawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        aboutUsDrawerLayout = findViewById(R.id.about_us_drawer_layout);
        toggle = new ActionBarDrawerToggle(this, aboutUsDrawerLayout, R.string.open, R.string.close);
        aboutUsDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.quotesGym:
                startActivity(new Intent(AboutUsActivity.this, GymActivity.class));
                break;
            case R.id.quotesFun:
                startActivity(new Intent(AboutUsActivity.this, SadActivity.class));
                break;
            case R.id.quotes_load_images:
                startActivity(new Intent(AboutUsActivity.this, LoadImages.class));
                break;
            case R.id.rate_us:
                super.rateUs();
                break;
        }
        aboutUsDrawerLayout.closeDrawer(GravityCompat.START);
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
