package amittpad.com.informationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Arrays;

import amittpad.com.informationapp.Fragments.Home;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    public static DrawerLayout drawerLayout;
    ArrayList<String> menuTitles = new ArrayList<>(Arrays.asList("Home", "Share", "Feedback", "Privacy Terms"));
    public static CustomDrawerAdapter customDrawerAdapter;
    private AdView mAdView;
    public static Boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent.getStringExtra("from").equalsIgnoreCase("notification"))
            flag = true;
        else flag = false;

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("D075C3BB04030944B7F9397F23409900")
                .build();
        mAdView.loadAd(adRequest);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        loadFragment(new Home(), false);
        setRecyclerData();

    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    private void setRecyclerData() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        customDrawerAdapter = new CustomDrawerAdapter(MainActivity.this, menuTitles);
        recyclerView.setAdapter(customDrawerAdapter);
    }


    @OnClick({R.id.menuHomeImage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menuHomeImage:
                drawerLayout.closeDrawers();
                CustomDrawerAdapter.selected_item = 0;
                customDrawerAdapter.notifyDataSetChanged();
                loadFragment(new Home(), false);
                break;
        }


    }

    public void loadFragment(Fragment fragment, Boolean bool) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        if (bool)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public void removeCurrentFragmentAndMoveBack(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.remove(fragment);
        trans.commit();
        fragmentManager.popBackStack();
    }
}