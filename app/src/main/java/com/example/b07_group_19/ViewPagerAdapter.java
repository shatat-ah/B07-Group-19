package com.example.b07_group_19;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.b07_group_19.login_signup_fragments.AdminLogin;
import com.example.b07_group_19.login_signup_fragments.StudentLogin;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 1: return new AdminLogin();
            default: return new StudentLogin();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
