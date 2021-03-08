package com.example.homework3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

public class CharacterFragment extends Fragment {
    private View view;

    private ImageView charImageIV;
    private TextView nameTV;
    private TextView statusTV;
    private TextView speciesTV;
    private TextView genderTV;
    private TextView originTV;
    private TextView locationTV;
    private TextView episodesTV;


    private Character character;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            character = new Character(getArguments().getByteArray("response"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        view=inflater.inflate(R.layout.fragment_character, container, false);

        charImageIV=view.findViewById(R.id.iv_charImage);
        Picasso.get().load(character.getImageURL()).into(charImageIV);

        nameTV=view.findViewById(R.id.tv_charName);
        nameTV.setText(character.getName());

        statusTV=view.findViewById(R.id.tv_charStatus);
        statusTV.setText("Status: "+character.getStatus());

        speciesTV=view.findViewById(R.id.tv_charSpecies);
        speciesTV.setText("Species: "+character.getSpecies());

        genderTV=view.findViewById(R.id.tv_charGender);
        genderTV.setText("Gender: "+character.getGender());

        originTV=view.findViewById(R.id.tv_charOrigin);
        originTV.setText("Origin: "+character.getOrigin());

        locationTV=view.findViewById(R.id.tv_charLocation);
        locationTV.setText("Location: "+character.getLocation());

        String[] tempEp=character.getEpisodes();
        String epString="";
        for (int i=0; i<tempEp.length; i++){
            epString=epString+tempEp[i]+", ";
        }
        epString=epString.substring(0,epString.length()-2);

        episodesTV=view.findViewById(R.id.tv_charEpisodes);
        episodesTV.setText("Appears in Episode(s): "+epString);



        return view;
    }
}
