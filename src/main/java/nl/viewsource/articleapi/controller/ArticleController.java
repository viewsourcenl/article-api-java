package nl.viewsource.articleapi.controller;


import nl.viewsource.articleapi.article.entity.ArticleValidationException;
import nl.viewsource.articleapi.article.usecase.CreateArticle;
import nl.viewsource.articleapi.article.usecase.FindArticle;
import nl.viewsource.articleapi.article.usecase.UpdateTags;
import nl.viewsource.articleapi.controller.model.ArticleWeb;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public record ArticleController(CreateArticle createArticle,
                                FindArticle findArticle,
                                UpdateTags updateTags) {

    public ArticleController {
        Objects.requireNonNull(createArticle);
        Objects.requireNonNull(findArticle);
        Objects.requireNonNull(updateTags);
    }

    public Optional<ArticleWeb> getArticle(String articleId) {
        return findArticle.findById(articleId).map(
                ArticleWeb::toArticleWeb
        );
    }

    public List<ArticleWeb> getAllArticles() {
        return findArticle.findAllArticles().stream()
                .map(ArticleWeb::toArticleWeb)
                .collect(Collectors.toList());
    }

    public ArticleWeb createArticle(@NotNull ArticleWeb articleWeb) throws ArticleValidationException {
        try {
            return ArticleWeb.toArticleWeb(createArticle.create(articleWeb.toArticle()));
        } catch (ParseException e) {
            throw new ArticleValidationException("Invalid date", e);
        }
    }

    public Optional<ArticleWeb> updateArticle(@NotNull String articleId, @NotNull ArticleWeb articleWeb) throws ArticleValidationException {
        try {
            return createArticle.update(articleId, articleWeb.toArticle()).map(ArticleWeb::toArticleWeb);
        } catch (ParseException e) {
            throw new ArticleValidationException("Invalid date", e);
        }
    }

    public Optional<ArticleWeb> addTags(@NotNull String articleId, @NotNull List<String> tags) {
        return updateTags.addTags(articleId, tags).map(ArticleWeb::toArticleWeb);
    }

    public Optional<ArticleWeb> removeTags(@NotNull String articleId, @NotNull List<String> tags) {
        return updateTags.removeTags(articleId, tags).map(ArticleWeb::toArticleWeb);
    }
}
