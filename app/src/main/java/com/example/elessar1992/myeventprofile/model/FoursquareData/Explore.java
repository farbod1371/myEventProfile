package com.example.elessar1992.myeventprofile.model.FoursquareData;

/**
 * Created by elessar1992 on 1/23/18.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class Explore {

    private Meta meta;
    private Response response;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     *
     * @param meta
     *     The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }


    /**
     *
     * @return
     *     The response
     */
    public Response getResponse() {
        return response;
    }

    /**
     *
     * @param response
     *     The response
     */
    public void setResponse(Response response) {
        this.response = response;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}