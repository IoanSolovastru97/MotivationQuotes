package nelu.com.motivationquotes;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView randomImage;
    private DrawerLayout mainLayoutDrawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button mainButton = findViewById(R.id.main);
//        final TextView text = findViewById(R.id.textMain);
//        mainButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent openGym = new Intent("app.java.nelu.com.motivationquotes.GymActivity");
//                startActivity(openGym);
//                text.setText("Button clicked");
//            }
//        });

        //Setting the upper left button
        mainLayoutDrawer = findViewById(R.id.drawerlayout); //Main Activity layout
        toggle = new ActionBarDrawerToggle(this, mainLayoutDrawer, R.string.open, R.string.close);
        mainLayoutDrawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
}
