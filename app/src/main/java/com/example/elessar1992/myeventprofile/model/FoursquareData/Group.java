package com.example.elessar1992.myeventprofile.model.FoursquareData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by elessar1992 on 1/30/18.
 */

public class Group
{
    private String type;
    private String name;
    private List<Item_> items = new ArrayList<Item_>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The items
     */
    public List<Item_> getItems() {
        return items;
    }

    /**
     *
     * @param items
     *     The items
     */
    public void setItems(List<Item_> items) {
        this.items = items;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value)
    {
        this.additionalProperties.put(name, value);
    }
}
