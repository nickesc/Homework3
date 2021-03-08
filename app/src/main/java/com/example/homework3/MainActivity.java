package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    Random rand = new Random();
    //private Character character;
    private Button button_character;
    private Button button_episode;
    private Button button_location;

    private static AsyncHttpClient asyncClient = new AsyncHttpClient();
    private int[] counts = {-1,1,1,1};
    private String[] cats= {"","character","location","episode"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        button_character=findViewById(R.id.button_character);
        button_location=findViewById(R.id.button_location);
        button_episode=findViewById(R.id.button_episode);

        button_character.setOnClickListener(v-> loadFragment(new CharacterFragment(),1));
        button_location.setOnClickListener(v-> loadFragment(new LocationFragment(),2));
        button_episode.setOnClickListener(v-> loadFragment(new EpisodeFragment(),3));


    }

    public void loadFragment(Fragment fragment, int category){

        String url="https://rickandmortyapi.com/api/"+cats[category];

        asyncClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject json = new JSONObject(new String(responseBody));
                    counts[category]=json.getJSONObject("info").getInt("count");
                    Log.d("help",""+counts[category]);

                    String id=cats[category];

                    if (category!=2){
                        id=id+"/"+(rand.nextInt(counts[category])+1);
                        //id=id+"/"+38;
                    }
                    //id=1;

                    Log.d("help", "rand id="+id);
                    asyncClient.get("https://rickandmortyapi.com/api/"+id, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            Bundle bundle = new Bundle();
                            bundle.putByteArray("response",responseBody);
                            fragment.setArguments(bundle);
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fcv_main,fragment);
                            fragmentTransaction.commit();
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("api error", new String((responseBody)));
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("api error", "API call failed");
            }
        });
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String CHANNEL_ID=getString(R.string.CHANNEL_ID);
            CharSequence name = getString(R.string.channelName);
            String description = getString(R.string.channelDescription);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.enableVibration(true);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}