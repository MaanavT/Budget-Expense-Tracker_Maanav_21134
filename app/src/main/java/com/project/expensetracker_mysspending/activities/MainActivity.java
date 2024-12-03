package com.project.expensetracker_mysspending.activities;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.project.expensetracker_mysspending.R;
import com.project.expensetracker_mysspending.adapters.SectionsPageAdapter;
import com.project.expensetracker_mysspending.fragments.BalanceFragment;
import com.project.expensetracker_mysspending.fragments.CustomBottomSheetDialogFragment;
import com.project.expensetracker_mysspending.fragments.ExpenseFragment;


public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    public static FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mViewPager=findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout=findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CustomBottomSheetDialogFragment().show(getSupportFragmentManager(), "Dialog");

            }
        });

    }


    private void setupViewPager() {
        setupViewPager();
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter=new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ExpenseFragment(),"Expenses");
        adapter.addFragment(new BalanceFragment(),"Balance");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "You have selected About menu", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,About.class);
                startActivity(intent);
                return true;
        }
        return false;
    }



}
