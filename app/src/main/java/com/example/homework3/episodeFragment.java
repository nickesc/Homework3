package com.example.homework3;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

public class episodeFragment extends Fragment {
    private View view;

    private Button moreInfoButton;

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

        moreInfoButton=view.findViewById(R.id.button_moreInfo);
        moreInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(v);
            }
        });

        return view;
    }

    public void sendNotification(View view){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData( Uri.parse(episode.getUrl()));

        Log.d("help",intent.resolveActivity(getContext().getPackageManager()).toString());

        if (intent.resolveActivity(getContext().getPackageManager())!=null) {


            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), getString(R.string.CHANNEL_ID))

                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(episode.getName())
                    .setContentText(episode.getUrl())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);



            PendingIntent pending = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pending);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
            notificationManager.notify(1, builder.build());
        }
        else{
            Log.e("ImplicitIntent", "Cannot handle Intent");
        }

    }
}
