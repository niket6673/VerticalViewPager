package com.keyboard.niket.verticalviewpager.adapter;

import android.os.Bundle;

import com.keyboard.niket.verticalviewpager.ChildFragment;
import com.keyboard.niket.verticalviewpager.modelclass.DummyDataModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<DummyDataModel> arrayList;

    public ViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<DummyDataModel> arrayList) {
        super(fm);
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        ChildFragment childFragment = new ChildFragment();
        DummyDataModel dummyDataModel = arrayList.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("id", dummyDataModel.getBooking_place_id());
        bundle.putString("image", dummyDataModel.getBooking_place_image());
        bundle.putString("title", dummyDataModel.getBooking_place_title());

        childFragment.setArguments(bundle);

        return childFragment;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
}
