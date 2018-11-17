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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GymActivity extends MainActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<String> images;
    private DatabaseReference mDatabaseRef;
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


        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        images = new ArrayList();

        //Firebase
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("quotes1");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String upload = postSnapshot.getValue(String.class);
                    images.add(upload);
                }

                adapter = new MyAdapter(images,GymActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(GymActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.quotesFun:
                startActivity(new Intent(GymActivity.this, SadQuotes.class));
                break;
            case R.id.quotes_load_images:
                startActivity(new Intent(GymActivity.this, LoadImages.class));
                break;
        }
        getMainLayoutDrawer().closeDrawer(GravityCompat.START);
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
