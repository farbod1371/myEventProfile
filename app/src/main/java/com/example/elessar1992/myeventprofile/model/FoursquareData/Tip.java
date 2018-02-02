package com.example.elessar1992.myeventprofile.model.FoursquareData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by elessar1992 on 1/30/18.
 */

public class Tip
{
    private String id;
    private Integer createdAt;
    private String text;
    private String type;
    private String canonicalUrl;
    private Photo photo;
    private String photourl;
    private Boolean logView;
    private Integer agreeCount;
    private Integer disagreeCount;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The createdAt
     */
    public Integer getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     *     The createdAt
     */
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
    }



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
     *     The canonicalUrl
     */
    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    /**
     *
     * @param canonicalUrl
     *     The canonicalUrl
     */
    public void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    /**
     *
     * @return
     *     The photo
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     *     The photo
     */
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    /**
     *
     * @return
     *     The photourl
     */
    public String getPhotourl() {
        return photourl;
    }

    /**
     *
     * @param photourl
     *     The photourl
     */
    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    /**
     *
     * @return
     *     The logView
     */
    public Boolean getLogView() {
        return logView;
    }

    /**
     *
     * @param logView
     *     The logView
     */
    public void setLogView(Boolean logView) {
        this.logView = logView;
    }

    /**
     *
     * @return
     *     The agreeCount
     */
    public Integer getAgreeCount() {
        return agreeCount;
    }

    /**
     *
     * @param agreeCount
     *     The agreeCount
     */
    public void setAgreeCount(Integer agreeCount) {
        this.agreeCount = agreeCount;
    }

    /**
     *
     * @return
     *     The disagreeCount
     */
    public Integer getDisagreeCount() {
        return disagreeCount;
    }

    /**
     *
     * @param disagreeCount
     *     The disagreeCount
     */
    public void setDisagreeCount(Integer disagreeCount) {
        this.disagreeCount = disagreeCount;
    }





    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

