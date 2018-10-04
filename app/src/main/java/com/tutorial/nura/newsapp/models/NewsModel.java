package com.tutorial.nura.newsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsModel {

    @SerializedName("articles")
    @Expose
    private List<News> news;

    public List<News> getNews(){
        return news;
    }

    public class News {

        @SerializedName("author")
        private String author;

        @SerializedName("title")
        private String title;

        @SerializedName("description")
        private String description;

        @SerializedName("url")
        private String url;

        @SerializedName("urlToImage")
        private String imageUrl;

        @SerializedName("publishedAt")
        private String publishDate;

        @SerializedName("source")
        private Source source;


        @Override
        public String toString() {
            return "News{" +
                    "author='" + author + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", url='" + url + '\'' +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", publishDate='" + publishDate + '\'' +
                    ", source=" + source.toString() +
                    '}';
        }

        public News() {
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        private class Source {

            @SerializedName("id")
            private String id;

            @SerializedName("name")
            private String name;

            @Override
            public String toString() {
                return "Source{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        '}';
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

        }

    }
}
