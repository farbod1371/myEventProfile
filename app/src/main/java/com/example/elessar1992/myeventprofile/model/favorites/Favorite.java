package com.example.elessar1992.myeventprofile.model.favorites;

/**
 * Created by elessar1992 on 1/30/18.
 */

public class Favorite
{
    private int favorite_id;
    private int user_id2;
    private String latitude;
    private String event_name;


    public int getFavorite_id()
    {
        return favorite_id;
    }

    public void setFavorite_id(int favorite_id)
    {
        this.favorite_id = favorite_id;
    }

    public int getUser_id2()
    {
        return user_id2;
    }

    public void setUser_id2(int user_id2)
    {
        this.user_id2 = user_id2;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getEvent_name()
    {
        return event_name;
    }

    public void setEvent_name(String event_name)
    {
        this.event_name = event_name;
    }
}
