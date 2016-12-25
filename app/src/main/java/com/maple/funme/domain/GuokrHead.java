package com.maple.funme.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by San on 2016/12/23.
 */

public class GuokrHead implements Serializable {

    @SerializedName("now")
    public String now;
    @SerializedName("ok")
    public boolean ok;
    @SerializedName("result")
    public List<ResultEntity> result;

    public static class ResultEntity implements Serializable {
        @SerializedName("article_id")
        public int articleId;
        @SerializedName("custom_title")
        public String customTitle;
        @SerializedName("ordinal")
        public int ordinal;
        @SerializedName("picture")
        public String picture;
    }
}
