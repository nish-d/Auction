package com.nishitadutta.auction.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.util.Log;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.nishitadutta.auction.Fragments.AddProductFragment_;
import com.nishitadutta.auction.Fragments.AllProductsFragment_;
import com.nishitadutta.auction.Fragments.MyProductsFragment_;
import com.nishitadutta.auction.Fragments.MyProfileFragment_;
import com.nishitadutta.auction.Fragments.MyRequestsFragment_;
import com.nishitadutta.auction.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;
    static boolean calledAlready;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseAuth auth= FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (!calledAlready) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(auth.getCurrentUser()==null) {

            /*Log.e(this.getLocalClassName(), "Not logged in");
            Intent in = new Intent(this, LoginActivity_.class);
            startActivity(in);

            finish();*/
        }
        else{

            /*Log.e(this.getLocalClassName(), "logged in");
            fragment= new AllProductsFragment_();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment).commit();*/

        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            /*FirebaseAuth.getInstance().signOut();
            Intent in=new Intent(this, LoginActivity_.class);
            startActivity(in);
            finish();*/
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add_product) {
            // Handle the camera action
            fragment=new AddProductFragment_();

        } else if (id == R.id.nav_my_products) {
            fragment= new MyProductsFragment_();

        } else if (id == R.id.nav_all_products) {
            fragment=new AllProductsFragment_();
        } else if (id == R.id.nav_my_profile) {
            fragment=new MyProfileFragment_();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();



    return true;
    }
}
