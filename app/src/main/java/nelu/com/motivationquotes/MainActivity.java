package nelu.com.motivationquotes;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

import java.util.List;

import nelu.com.motivationquotes.utilis.ImageProcessesing;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private List<String> images;
    private DrawerLayout mainLayoutDrawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the upper left button
        mainLayoutDrawer = findViewById(R.id.main_drawer_layout);
        toggle = new ActionBarDrawerToggle(this, mainLayoutDrawer, R.string.open, R.string.close);
        mainLayoutDrawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.main_recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        images = ImageProcessesing.getListImage(MainActivity.this, recyclerView,"quotes1");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Nu stiu ce face, merge si fara
    @Override
    public void onBackPressed() {
        if (mainLayoutDrawer.isDrawerOpen(GravityCompat.START)) {
            mainLayoutDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.quotesGym:
                startActivity(new Intent(MainActivity.this, GymActivity.class));
                break;
            case R.id.quotesFun:
                startActivity(new Intent(MainActivity.this, SadActivity.class));
                break;
            case R.id.quotes_load_images:
                startActivity(new Intent(MainActivity.this, LoadImages.class));
                break;
            case R.id.about_us:
                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                break;
            case R.id.rate_us:
                rateUs();
                break;
        }
        mainLayoutDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void rateUs() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.android.chrome")));
//                    For my app
//                    Uri.parse("market://details?id=" + getPackageName())));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details/?id=" + getPackageName())));
        }
    }

}
