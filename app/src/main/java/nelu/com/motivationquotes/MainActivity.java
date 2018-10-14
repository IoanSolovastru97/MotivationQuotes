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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
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
        toggle = new ActionBarDrawerToggle(this, mainLayoutDrawer, R.string.open, R.string.close);
        mainLayoutDrawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupNavigationView();

        generateRandomImage();

    }

    private void setupNavigationView() {
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.quotesGym:
                        startActivity(new Intent(MainActivity.this, GymActivity.class));
                        break;
                    case R.id.quotesFun:

                        break;
                }
                return false;
            }
        });
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
}
