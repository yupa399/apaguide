package com.example.apaguide;

import android.os.Bundle;

import com.example.apaguide.model.Example;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import java.io.IOException;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {


    private static final String[] Eample_Data = new String[]{
            "{ id: 1, categoryId: 1, subCategoryId: 1, sequenceNo: 1, description1: \"Description11\", example: \"Smith, A., Brown, B., &amp; Chan, C. (2014). <i>New formations in international business.</i> Chicago, IL: Hoffman\", authorNumber: 1,  hasDate: 0 }",
            "{ id: 2, categoryId: 1, subCategoryId: 2, sequenceNo: 1, description1: \"Description12\", example: \"Example12\", authorNumber: 1,  hasDate: 0 }",
    };

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_book, R.id.nav_webpage,
                R.id.nav_magazine, R.id.nav_journal, R.id.nav_material, R.id.nav_faq, R.id.nav_about)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //loadContents(); // create sample contents data
    }

    // create sample contents data
    private void loadContents() {
        Realm.deleteRealm(Realm.getDefaultConfiguration());
        Realm realm = Realm.getDefaultInstance();   // Get a Realm instance for this thread
        // create sample data
        for(final String json : Eample_Data){
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.createObjectFromJson(Example.class, json);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
