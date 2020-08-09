package com.usamaqadeer.freshveg.activities.Admin;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.usamaqadeer.freshveg.R;
import com.usamaqadeer.freshveg.activities.Admin.Fragments.AssignOrdersFragment;
import com.usamaqadeer.freshveg.activities.Admin.Fragments.CategoriesFragment;
import com.usamaqadeer.freshveg.activities.Admin.Fragments.DeliveryBoysFragment;
import com.usamaqadeer.freshveg.activities.Admin.Fragments.OrdersFragment;
import com.usamaqadeer.freshveg.activities.Admin.Fragments.ProductsFragment;

public class AdminDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public final FragmentManager fragmentManager = getSupportFragmentManager();
    public Fragment fragmentProducts = new ProductsFragment();
    public Fragment fragmentCategories = new CategoriesFragment();
    public Fragment fragmentDeliveryBoys = new DeliveryBoysFragment();
    public Fragment fragmentOrders = new OrdersFragment();
    public Fragment fragmentAssignOrders = new AssignOrdersFragment();
    public static Fragment activeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadInitialFragment(fragmentProducts);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    /* LOAD INITIAL FRAGMENT IN FRAGMENT CONTAINER*/
    private void loadInitialFragment(Fragment fragment) {
        activeFragment = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.disallowAddToBackStack();
        transaction.commit();
    }

    /* LOAD FRAGMENT IN FRAGMENT CONTAINER AS PER DEMAND*/
    private void loadFragment(Fragment fragment) {
        activeFragment = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.main_container, fragment);
        transaction.disallowAddToBackStack();
        transaction.commit();
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
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settings clicked.", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_products)
            loadFragment(fragmentProducts);
        else if (id == R.id.nav_categories)
            loadFragment(fragmentCategories);
        else if (id == R.id.nav_deliveryboys)
            loadFragment(fragmentDeliveryBoys);
        else if (id == R.id.nav_view_order)
            loadFragment(fragmentOrders);
        else if (id == R.id.nav_assign_order)
            loadFragment(fragmentAssignOrders);
        else if (id == R.id.nav_admin_signout)
            finish();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
