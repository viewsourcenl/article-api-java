package nl.viewsource.articleapi.controller;


import nl.viewsource.articleapi.article.entity.ArticleValidationException;
import nl.viewsource.articleapi.article.usecase.CreateArticle;
import nl.viewsource.articleapi.article.usecase.FindArticle;
import nl.viewsource.articleapi.controller.model.ArticleWeb;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArticleController {
    final CreateArticle createArticle;
    final FindArticle findArticle;

    public ArticleController(CreateArticle createArticle, FindArticle findArticle) {
        this.createArticle = createArticle;
        this.findArticle = findArticle;
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
}
