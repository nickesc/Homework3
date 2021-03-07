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

    private static AsyncHttpClient client = new AsyncHttpClient();
    private int[] counts = {-1,1,1,1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getItemCount(1);

        //getItemCount(2);
        //getItemCount(3);

        button_character=findViewById(R.id.button_character);

        button_character.setOnClickListener(v-> loadCharacterFragment(new characterFragment()));


    }

    public void getItemCount(int categoryNum){
        String[] cats= {"","character","location","episode"};
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
    public void loadCharacterFragment(Fragment fragment){
        int id=rand.nextInt(counts[1])+1;
        //id=1;
        Log.d("help", "rand id="+id);
        client.get("https://rickandmortyapi.com/api/character/"+id, new AsyncHttpResponseHandler() {
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