package nelu.com.motivationquotes;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView randomImage;
    private DrawerLayout mainLayoutDrawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the upper left button
        mainLayoutDrawer = findViewById(R.id.main_drawer_layout); //Main Activity layout
        toggle = new ActionBarDrawerToggle(this, mainLayoutDrawer, R.string.open, R.string.close);
        mainLayoutDrawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //make an utility class to call random image
        generateRandomImage();

    }

    private void generateRandomImage() {
        randomImage = findViewById(R.id.randomImage);
        randomImage.setBackground(getDrawable(R.drawable.quote1));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            Log.d("1","HERE");
            return true;
        }
        Log.d("1","THERE");
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
                startActivity(new Intent(MainActivity.this, SadQuotes.class));
                break;
            case R.id.quotes_load_images:
                startActivity(new Intent(MainActivity.this, LoadImages.class));
                break;
            case R.id.rate_us:
                rateUs();
                break;
        }
        mainLayoutDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void rateUs() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=" + "com.android.chrome")));
//                    For my app
//                    Uri.parse("market://details?id=" + getPackageName())));
        }catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details/?id=" + getPackageName())));
        }
    }

    public DrawerLayout getMainLayoutDrawer() {
        return mainLayoutDrawer;
    }

    public ActionBarDrawerToggle getToggle() {
        return toggle;
    }
}
