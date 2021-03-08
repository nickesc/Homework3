package com.example.homework3;

import org.json.JSONException;
import org.json.JSONObject;

public class Location {

    private String name;
    private String type;
    private String dimension;

    public Location(byte[] response) throws JSONException {
        JSONObject json = new JSONObject(new String(response));

        this.setName(json.getString("name"));
        this.setDimension(json.getString("dimension"));
        this.setType(json.getString("type"));


    }

    private void setName(String name) {
        this.name = name;
    }

    private void setDimension(String dimension) {
        this.dimension = dimension;
    }

    private void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDimension() {
        return dimension;
    }

    public String getType() {
        return type;
    }
}

