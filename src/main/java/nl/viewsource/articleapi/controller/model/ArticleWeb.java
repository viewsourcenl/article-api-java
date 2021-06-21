package nl.viewsource.articleapi.controller.model;

import nl.viewsource.articleapi.article.entity.Article;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class ArticleWeb {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date.toString();
    }

    public Article toArticle() throws ParseException {
       return Article.builder()
               .id(id)
               .title(title)
               .description(description)
               .tags(tags)
               .link(link)
               .image(image)
               .date(sdf.parse(date))
               .build();
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    public static ArticleWeb toArticleWeb(Article article) {
        var web = new ArticleWeb();
        web.id = article.id;
        web.title = article.title;
        web.description = article.description;
        web.tags = article.tags;
        web.link = article.link;
        web.image = article.image;
        web.date = sdf.format(article.date);
        return web;
    }

    private String id;
    private String title;
    private String description;
    private List<String> tags;
    private String link;
    private String image;
    private String date;
}
