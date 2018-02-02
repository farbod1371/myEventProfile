package com.example.elessar1992.myeventprofile.model.Events;

/**
 * Created by elessar1992 on 1/29/18.
 */

public class Event
{
    private int event_id;
    private int user_id;
    private String description;
    private String time;


    public int getEvent_Id()
    {
        return event_id;
    }

    public void setEvent_id(int event_id)
    {
        this.event_id = event_id;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

}
