package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

    private static AsyncHttpClient client = new AsyncHttpClient();
    private int[] counts = {-1,1,1,1};
    private String[] cats= {"","character","location","episode"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getItemCount(1);

        //getItemCount(2);
        getItemCount(3);

        button_character=findViewById(R.id.button_character);
        button_episode=findViewById(R.id.button_episode);

        button_character.setOnClickListener(v-> loadFragment(new characterFragment(),1));
        button_episode.setOnClickListener(v-> loadFragment(new episodeFragment(),3));


    }

    public void getItemCount(int categoryNum){

        String url="https://rickandmortyapi.com/api/"+cats[categoryNum];
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject json = new JSONObject(new String(responseBody));
                    counts[categoryNum]=json.getJSONObject("info").getInt("count");
                    Log.d("help",""+counts[categoryNum]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api error", new String((responseBody)));
            }
        });
    }
    public void loadFragment(Fragment fragment, int category){
        String id=cats[category];

        if (category!=2){
            id=id+"/"+(rand.nextInt(counts[category])+1);
        }
        //id=1;
        Log.d("help", "rand id="+id);
        client.get("https://rickandmortyapi.com/api/"+id, new AsyncHttpResponseHandler() {
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
    }
}