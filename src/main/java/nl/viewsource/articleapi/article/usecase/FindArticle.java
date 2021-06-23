package nl.viewsource.articleapi.article.usecase;

import nl.viewsource.articleapi.article.entity.Article;
import nl.viewsource.articleapi.article.usecase.port.ArticleRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record FindArticle(ArticleRepository articleRepository) {

    public FindArticle {
        Objects.requireNonNull(articleRepository);
    }

    public Optional<Article> findById(final String id) {
        return articleRepository.findById(id);
    }

    public List<Article> findAllArticles() {
        return articleRepository.findAllArticles();
    }
}
