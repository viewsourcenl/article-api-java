package nl.viewsource.articleapi.article.entity;

import nl.viewsource.articleapi.article.entity.port.IdValidator;
import nl.viewsource.articleapi.article.entity.port.UrlValidator;

import java.util.Objects;

record ArticleValidatorImpl(IdValidator idValidator, UrlValidator urlValidator) implements ArticleValidator {

    public ArticleValidatorImpl {
        Objects.requireNonNull(idValidator);
        Objects.requireNonNull(urlValidator);
    }

    @Override
    public void validate(Article article) throws ArticleValidationException {
        if (!this.idValidator.isValid(article.id)) {
            throw new ArticleValidationException("Article must have a valid id");
        }
        if (article.title == null) {
            throw new ArticleValidationException("Article must have a title");
        }
        if (article.title.length() < 3) {
            throw new ArticleValidationException("Article title should be a minimal of 3 characters");
        }
        if (article.description == null) {
            throw new ArticleValidationException("Article must have a description");
        }
        if (article.link == null) {
            throw new ArticleValidationException("Article must have a link");
        }
        if (!this.urlValidator.isValid(article.link)) {
            throw new ArticleValidationException("Article must have a valid link");
        }
        if (article.image == null) {
            throw new ArticleValidationException("Article must have an image");
        }
        if (!this.urlValidator.isValid(article.image)) {
            throw new ArticleValidationException("Article must have a valid image url");
        }
        if (article.date == null) {
            throw new ArticleValidationException("Article must have a date");
        }
    }
}
