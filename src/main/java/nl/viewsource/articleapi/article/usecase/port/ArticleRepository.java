package nl.viewsource.articleapi.article.usecase.port;

import nl.viewsource.articleapi.article.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    public Article create(Article article);

    public Optional<Article> findById(String id);

    public List<Article> findAllArticles();

    public Article replace(Article article);
}
