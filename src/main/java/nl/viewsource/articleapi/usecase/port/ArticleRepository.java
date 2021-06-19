package nl.viewsource.articleapi.usecase.port;

import nl.viewsource.articleapi.domain.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    public Article create(Article article);

    public Optional<Article> findById(String id);

    public List<Article> findAllArticles();
}
