package nl.viewsource.articleapi.domain.entity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Article {
   private static class Id {
       static String generate() {
           return "myId";
       }
       static boolean isValid(String id) {
           return "myId".equals(id);
       }
   }

    public final String id;
    public final String hash;
    public final String title;
    public final String description;
    public final List<String> tags;
    public final String link;
    public final String image;
    public final Date date;

    private Article(Builder builder) {
        this.id = builder.id;
        this.hash = builder.hash;
        this.title = builder.title;
        this.description = builder.description;
        this.tags = builder.tags;
        this.link = builder.link;
        this.image = builder.image;
        this.date = builder.date;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String hash;
        private String title;
        private String description;
        private List<String> tags;
        private String link;
        private String image;
        private Date date;

        public Builder id (String id){
            this.id = id;
            return this;
        }
        public Builder title (String title){
            this.title = title;
            return this;
        }
        public Builder description (String description){
            this.description = description;
            return this;
        }
        public Builder tags (List<String> tags){
            this.tags = tags;
            return this;
        }
        public Builder tags (String... tags) {
            this.tags = List.of(tags);
            return this;
        }
        public Builder link (String link){
            this.link = link;
            return this;
        }
        public Builder image (String image){
            this.image = image;
            return this;
        }
        public Builder date (Date date){
            this.date = date;
            return this;
        }
        public Article build () {
            return new Article(this);
        }
    }
}
