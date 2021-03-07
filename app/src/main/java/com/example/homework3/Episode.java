package com.example.homework3;

import org.json.JSONException;
import org.json.JSONObject;

public class Episode {

    private int id;
    private String number;
    private String name;
    private String date;
    private String[] characters;

    private String url;

    public Episode(byte[] response) throws JSONException {
        JSONObject json = new JSONObject(new String(response));

        this.setName(json.getString("name"));
        this.setNumber(json.getString("episode"));
        this.setDate(json.getString("air_date"));


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

    public void setCharacters(String[] characters) {
        this.characters = characters;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
