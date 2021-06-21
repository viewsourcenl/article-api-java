package nl.viewsource.articleapi.article.usecase;

import nl.viewsource.articleapi.article.entity.Article;
import nl.viewsource.articleapi.article.usecase.port.ArticleRepository;

import java.util.*;
import java.util.stream.Collectors;

public final class UpdateTags {
    private final ArticleRepository articleRepository;

    public UpdateTags(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Optional<Article> addTags(final String articleId, final List<String> tags) {
        return articleRepository
                .findById(articleId)
                .map(article -> {
                    var uniqueTags = new HashSet<String>();
                    uniqueTags.addAll(article.tags);
                    uniqueTags.addAll(tags);

                    var newTags = List.copyOf(uniqueTags);

                    return Article.builder(article).tags(newTags).build();
                }).map(articleRepository::replace);

    }

    public Optional<Article> removeTags(final String articleId, final List<String> tags) {
        return articleRepository
                .findById(articleId)
                .map(article -> {
                    var newTags = article.tags.stream().filter(
                           tag -> !tags.contains(tag)
                    ).collect(Collectors.toList());

                    return Article.builder(article).tags(newTags).build();
                }).map(articleRepository::replace);
    }
}
