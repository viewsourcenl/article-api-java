package nl.viewsource.articleapi.article.usecase;

import nl.viewsource.articleapi.article.entity.Article;
import nl.viewsource.articleapi.article.entity.ArticleValidationException;
import nl.viewsource.articleapi.article.entity.ArticleValidator;
import nl.viewsource.articleapi.article.usecase.port.ArticleRepository;
import nl.viewsource.articleapi.article.usecase.port.IdGenerator;

import java.util.Objects;
import java.util.Optional;

public record CreateArticle(ArticleRepository articleRepository, IdGenerator idGenerator, ArticleValidator articleValidator) {

    public CreateArticle {
        Objects.requireNonNull(articleRepository);
        Objects.requireNonNull(idGenerator);
        Objects.requireNonNull(articleValidator);
    }

    public Article create(final Article article) throws ArticleValidationException {
        var articleToSave = Article.builder(article)
                .id(idGenerator.generate())
                .build();

        articleValidator.validate(articleToSave);

        return articleRepository.create(articleToSave);
    }

    public Optional<Article> update(String articleId, final Article article) throws ArticleValidationException {
        var optionalArticle = articleRepository.findById(articleId);

        if (optionalArticle.isEmpty()) {
            return optionalArticle;
        }

        var existingArticle = optionalArticle.get();
        var articleToSave = Article.builder(article).id(articleId).build();

        articleValidator.validate(articleToSave);

        return Optional.of(articleRepository.replace(articleToSave));
    }
}
