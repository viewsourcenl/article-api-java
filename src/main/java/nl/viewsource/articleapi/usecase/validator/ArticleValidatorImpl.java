package nl.viewsource.articleapi.usecase.validator;

import nl.viewsource.articleapi.domain.entity.Article;
import nl.viewsource.articleapi.usecase.exception.*;
import nl.viewsource.articleapi.usecase.port.*;

import javax.validation.constraints.NotNull;
import java.net.MalformedURLException;
import java.net.URL;

public class ArticleValidatorImpl implements nl.viewsource.articleapi.usecase.port.ArticleValidator {
    private final UrlValidator urlValidator;
    private final IdGenerator idGenerator;

    public ArticleValidatorImpl(final IdGenerator idGenerator, final UrlValidator urlValidator) {
        this.idGenerator = idGenerator;
        this.urlValidator = urlValidator;
    }

    @Override
    public void validate(Article article) throws ArticleValidationException {
        if (!this.idGenerator.isValid(article.id)) {
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
        if (article.tags == null) {
            throw new ArticleValidationException("Article must have tags list");
        }
    }
}
