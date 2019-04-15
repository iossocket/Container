package com.thoughtworks.container;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thoughtworks.container.holder.ContainerActivity;

public class DashboardFragment extends Fragment {

    private Toolbar toolbar;
    private Button showRNButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        this.toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle("Dashboard");

        this.showRNButton = view.findViewById(R.id.showRN);
        this.showRNButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ContainerActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
}
