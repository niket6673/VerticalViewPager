package com.keyboard.niket.verticalviewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChildFragment extends Fragment {


    public ChildFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child, container, false);

        TextView id_tv = view.findViewById(R.id.booking_id_tv);
        TextView titile_tv = view.findViewById(R.id.title_tv);
        ImageView booking_iv = view.findViewById(R.id.booking_image);
        String image;

        Bundle bundle = getArguments();

        if (bundle != null) {
            id_tv.setText(bundle.getString("id"));
            titile_tv.setText(bundle.getString("title"));
            image = bundle.getString("image");

            Glide.with(this).load(image).into(booking_iv);


        }

        return view;
    }
}
