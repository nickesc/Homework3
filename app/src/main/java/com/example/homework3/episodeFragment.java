package com.example.homework3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;

public class episodeFragment extends Fragment {
    private View view;

    private TextView numberTV;
    private TextView nameTV;
    private TextView dateTV;
    private TextView charactersTV;


    private Episode episode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_episode, container, false);

        try {
            episode = new Episode(getArguments().getByteArray("response"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        numberTV=view.findViewById(R.id.tv_number);
        numberTV.setText(episode.getNumber());

        nameTV=view.findViewById(R.id.tv_name);
        nameTV.setText(episode.getName());

        dateTV=view.findViewById(R.id.tv_date);
        dateTV.setText(episode.getDate());




        return view;
    }
}
