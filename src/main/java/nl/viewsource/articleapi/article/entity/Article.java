package nl.viewsource.articleapi.article.entity;

import java.util.*;

public final class Article {
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

    @Override
    public String toString() {
        return "["
                + "id: " + id
                + ", title: " + title
                + ", description: " + description
                + ", tags: " + tags
                + ", link: " + link
                + ", image: " + image
                + ", date: " + date
                + "]";
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;

        Article article = (Article) that;

        return Objects.equals(id, article.id)
                && Objects.equals(title, article.title)
                && Objects.equals(description, article.description)
                && Objects.equals(tags, article.tags)
                && Objects.equals(link, article.link)
                && Objects.equals(image, article.image)
                && Objects.equals(date, article.date)
                ;

    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Article article) {
        return new Builder(article);
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

        private Builder() {
        }

        private Builder(Article article) {
            this.id = article.id;
            this.hash = article.hash;
            this.title = article.title;
            this.description = article.description;
            this.tags = article.tags;
            this.link = article.link;
            this.image = article.image;
            this.date = article.date;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder tags(String... tags) {
            this.tags = List.of(tags);
            return this;
        }

        public Builder link(String link) {
            this.link = link;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Article build() {
            if (tags == null) {
                tags = Collections.emptyList();
            } else {
                tags = Collections.unmodifiableList(tags);
            }

            // TODO compute hash

            return new Article(this);
        }
    }
}