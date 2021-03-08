package com.example.homework3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

public class episodeFragment extends Fragment {
    private View view;

    private TextView numberTV;
    private TextView nameTV;
    private TextView dateTV;
    private TextView charactersTV1;
    private TextView charactersTV2;
    private TextView charactersTV3;

    private static AsyncHttpClient asyncClient = new AsyncHttpClient();


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

        final String[] charString = {"","",""};


        charactersTV1=view.findViewById(R.id.tv_characters1);
        charactersTV2=view.findViewById(R.id.tv_characters2);
        charactersTV3=view.findViewById(R.id.tv_characters3);

        asyncClient.get(episode.getCharacters()[0], new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String name=new JSONObject(new String(responseBody)).getString("name");
                    if(!name.equals("")){
                    charactersTV1.setText(name);}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api error", new String((responseBody)));
            }
        });

        asyncClient.get(episode.getCharacters()[1], new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String name=new JSONObject(new String(responseBody)).getString("name");
                    if(!name.equals("")){
                        charactersTV2.setText(name);}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api error", new String((responseBody)));
            }
        });

        asyncClient.get(episode.getCharacters()[2], new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String name=new JSONObject(new String(responseBody)).getString("name");
                    if(!name.equals("")){
                        charactersTV3.setText(name);}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api error", new String((responseBody)));
            }
        });
        return view;
    }
}
