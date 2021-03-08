package com.example.homework3;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Episode {

    private int id;
    private String number;
    private String name;
    private String date;
    private String[] characters = {"","",""};

    private String url;



    public Episode(byte[] response) throws JSONException {
        JSONObject json = new JSONObject(new String(response));

        this.setName(json.getString("name"));
        this.setNumber(json.getString("episode"));
        this.setDate(json.getString("air_date"));
        setCharacters(json.getJSONArray("characters"));

        this.setUrl(json.getString("name"));
        Log.d("help",this.getUrl());


    }


    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String[] getCharacters() {
        return characters;
    }

    public String getUrl() {
        return url;
    }



    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCharacters(JSONArray characters) throws JSONException {
        String[] tempArray={"","",""};
        for (int i=0; i<characters.length() && i<3;i++){

            this.characters[i]=characters.getString(i);

        }
    }

    public void setUrl(String name) {
        String[] nameArray=name.split(" ");
        String url="";
        for(int i=0;i<nameArray.length-1;i++){
            url=url+nameArray[i]+"_";
        }
        url="https://rickandmorty.fandom.com/wiki/"+url+nameArray[nameArray.length-1];
        this.url = url;
    }
}
