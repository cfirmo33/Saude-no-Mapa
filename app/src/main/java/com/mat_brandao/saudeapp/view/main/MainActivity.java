package com.mat_brandao.saudeapp.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.mat_brandao.saudeapp.R;
import com.mat_brandao.saudeapp.view.base.BaseActivity;
import com.mat_brandao.saudeapp.view.base.BasePresenter;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.frame_content)
    FrameLayout frameContent;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private MainPresenterImpl mPresenter;

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navigationView.setNavigationItemSelectedListener(mPresenter);

        mPresenter = new MainPresenterImpl(this, this);
    }

    @Override
    public void showToast(String text) {
        super.showToast(text);
    }

    @Override
    public void goToActivity(Class<?> activity) {
        super.goToActivity(activity);
    }

    @Override
    public void goToActivity(Intent intent) {
        super.goToActivity(intent);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showNoConnectionSnackBar() {
        // TODO: 16/09/2016  
    }

    @Override
    public void showProgressDialog(String message) {
        super.showProgressDialog(this, message);
    }

    @Override
    public void dismissProgressDialog() {
        super.dismissProgressDialog();
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_content, fragment)
                .commit();
    }

    @Override
    public void closeDrawer() {
        drawerLayout.closeDrawers();
    }

    @Override
    public void setProfileNameText(String name) {
        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.profile_name_text)).setText(name);
    }

    @Override
    public void setProfileEmailText(String email) {
        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.profile_email_text)).setText(email);
    }

    @Override
    public void setProfileImage(String profilePhotoUrl) {
        Picasso.with(this)
                .load(profilePhotoUrl)
                .error(R.drawable.avatar_placeholder)
                .into(((ImageView) navigationView.getHeaderView(0).findViewById(R.id.profile_image)));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}