package nelu.com.motivationquotes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
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
        mainLayoutDrawer = findViewById(R.id.drawerlayout); //Main Activity layout
        navigationView = findViewById(R.id.navigation_view);
        toggle = new ActionBarDrawerToggle(this, mainLayoutDrawer, R.string.open, R.string.close);
        mainLayoutDrawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

        generateRandomImage();

    }

    private void generateRandomImage() {
        randomImage = findViewById(R.id.randomImage);
        randomImage.setBackground(getDrawable(R.drawable.quote1));
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
                startActivity(new Intent(MainActivity.this, SadQuotes.class));
                break;
        }
        mainLayoutDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
