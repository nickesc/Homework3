package com.example.homework3;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Character {

    private int id;
    private String name;
    private String status;
    private String species;
    private String gender;
    private String origin;
    private String location;
    private String imageURL;
    private String[] episodes;

    public Character(byte[] response) throws JSONException {
        JSONObject json = new JSONObject(new String(response));

        setId(json.getInt("id"));
        setName(json.getString("name"));
        setStatus(json.getString("status"));
        setSpecies(json.getString("species"));
        setGender(json.getString("gender"));
        setOrigin(json.getJSONObject("origin"));
        setLocation(json.getJSONObject("location"));
        setImageURL(json.getString("image"));
        setEpisodes(json.getJSONArray("episode"));

    }

    public void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setStatus(String status) {
        this.status = status;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    private void setGender(String gender) {
        this.gender = gender;
    }

    private void setOrigin(JSONObject origin) throws JSONException {
        this.origin = origin.getString("name");
    }

    public void setLocation(JSONObject location) throws JSONException {
        this.location = location.getString("name");
    }

    private void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    private void setEpisodes(JSONArray episodes) throws JSONException {


        String[] episodeArray=new String[episodes.length()];
        for(int i=0; i<episodes.length();i++){
            episodeArray[i]=episodes.getString(i).substring(40);
        }
        this.episodes = episodeArray;
        Log.d("help", Arrays.toString(episodeArray));


    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getGender() {
        return gender;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLocation() {
        return location;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String[] getEpisodes() {
        return episodes;
    }
}
