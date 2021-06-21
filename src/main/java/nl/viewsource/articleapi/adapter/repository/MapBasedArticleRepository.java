package nl.viewsource.articleapi.adapter.repository;

import nl.viewsource.articleapi.article.entity.Article;
import nl.viewsource.articleapi.article.usecase.port.ArticleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MapBasedArticleRepository implements ArticleRepository {
    private Map<String, Article> map = new HashMap<>();
    @Override
    public Article create(Article article) {
        if (map.containsKey(article.id)) {
            throw new RuntimeException("Article already exists");
        }
        map.put(article.id, article);
        return article;
    }

    @Override
    public Optional<Article> findById(String id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public List<Article> findAllArticles() {
        return List.copyOf(map.values());
    }

    @Override
    public Article replace(Article article) {
        map.put(article.id, article);
        return article;
    }
}
