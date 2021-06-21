package nl.viewsource.articleapi.article.usecase;

import nl.viewsource.articleapi.article.entity.Article;
import nl.viewsource.articleapi.article.usecase.port.ArticleRepository;

import java.util.List;
import java.util.Optional;

public final class FindArticle {
    private final ArticleRepository articleRepository;

    public FindArticle(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Optional<Article> findById(final String id) {
        return articleRepository.findById(id);
    }

    public List<Article> findAllArticles() {
        return articleRepository.findAllArticles();
    }
}
