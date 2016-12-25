package com.maple.funme.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by San on 2016/12/24.
 */

public class MeizhiEntity {

    @SerializedName("error")
    public boolean error;
    @SerializedName("results")
    public List<ResultsEntity> results;

    public static class ResultsEntity {
        @SerializedName("_id")
        public String id;
        @SerializedName("createdAt")
        public String createdAt;
        @SerializedName("desc")
        public String desc;
        @SerializedName("publishedAt")
        public String publishedAt;
        @SerializedName("source")
        public String source;
        @SerializedName("type")
        public String type;
        @SerializedName("url")
        public String url;
        @SerializedName("used")
        public boolean used;
        @SerializedName("who")
        public String who;
    }

}
