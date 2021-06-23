package nl.viewsource.articleapi.article.entity;

import nl.viewsource.articleapi.article.entity.port.IdValidator;
import nl.viewsource.articleapi.article.entity.port.UrlValidator;

public record ArticleValidatorFactory(IdValidator idValidator,
                                      UrlValidator urlValidator) {

    public ArticleValidator articleValidator() {
        return new ArticleValidatorImpl(idValidator, urlValidator);
    }
}
