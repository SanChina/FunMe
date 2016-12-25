package com.maple.funme.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by San on 2016/12/23.
 */

public class GuokrBody implements Serializable {

    @SerializedName("now")
    public String now;
    @SerializedName("ok")
    public boolean ok;
    @SerializedName("result")
    public List<ResultEntity> result;

    public static class ResultEntity {
        @SerializedName("link_v2_sync_img")
        public String linkV2SyncImg;
        @SerializedName("source_name")
        public String sourceName;
        @SerializedName("video_url")
        public String videoUrl;
        @SerializedName("current_user_has_collected")
        public boolean currentUserHasCollected;
        @SerializedName("likings_count")
        public int likingsCount;
        @SerializedName("video_duration")
        public Object videoDuration;
        @SerializedName("id")
        public int id;
        @SerializedName("category")
        public String category;
        @SerializedName("style")
        public String style;
        @SerializedName("title")
        public String title;
        @SerializedName("source_data")
        public SourceDataEntity sourceData;
        @SerializedName("headline_img_tb")
        public String headlineImgTb;
        @SerializedName("link_v2")
        public String linkV2;
        @SerializedName("date_picked")
        public double datePicked;
        @SerializedName("is_top")
        public boolean isTop;
        @SerializedName("link")
        public String link;
        @SerializedName("headline_img")
        public String headlineImg;
        @SerializedName("replies_count")
        public int repliesCount;
        @SerializedName("current_user_has_liked")
        public boolean currentUserHasLiked;
        @SerializedName("page_source")
        public String pageSource;
        @SerializedName("author")
        public String author;
        @SerializedName("summary")
        public String summary;
        @SerializedName("source")
        public String source;
        @SerializedName("reply_root_id")
        public int replyRootId;
        @SerializedName("date_created")
        public double dateCreated;
        @SerializedName("images")
        public List<String> images;

        public static class SourceDataEntity implements Serializable {
            @SerializedName("image")
            public String image;
            @SerializedName("summary")
            public String summary;
            @SerializedName("id")
            public int id;
            @SerializedName("key")
            public String key;
            @SerializedName("title")
            public String title;
        }
    }
}
