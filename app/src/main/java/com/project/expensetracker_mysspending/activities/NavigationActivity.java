package com.project.expensetracker_mysspending.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.project.expensetracker_mysspending.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;
import com.project.expensetracker_mysspending.R;

import android.content.SharedPreferences;
public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        // Setting up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // DrawerLayout and toggle setup
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // NavigationView setup
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Setting up the header view for username and sign-out button
        View headerView = navigationView.getHeaderView(0);
        Button signOut = headerView.findViewById(R.id.navSignOutButton);
        final CheckBox staySignedIn = headerView.findViewById(R.id.staySignedIn);

        // Get the username from saved preferences or the login intent
        if (SavedPreferences.isSignedIn(this)) {
            username = SavedPreferences.getSavedUsername(this);
            staySignedIn.setChecked(true);
        } else {
            username = getIntent().getStringExtra("USERNAME");
        }

        // Display the username in the header
        TextView profileUserName = headerView.findViewById(R.id.profileUsername);
        profileUserName.setText(username);

        // Set the sign-out button logic
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove stored username and uncheck the "stay signed in" option
                SavedPreferences.removeUsername(NavigationActivity.this);
                staySignedIn.setChecked(false);

                // Navigate to the LoginActivity
                Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        // Set the stay signed-in checkbox logic
        staySignedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staySignedIn.isChecked()) {
                    SavedPreferences.saveUsername(NavigationActivity.this, username);
                } else {
                    SavedPreferences.removeUsername(NavigationActivity.this);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //@Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R., menu);
//        return true;
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // Handle other action bar items if needed
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
