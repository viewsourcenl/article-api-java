package nl.viewsource.articleapi.article.entity;

import nl.viewsource.articleapi.article.entity.port.IdValidator;
import nl.viewsource.articleapi.article.entity.port.UrlValidator;

public class ArticleValidatorFactory {
    private final IdValidator idValidator;
    private final UrlValidator urlValidator;

    public ArticleValidatorFactory(IdValidator idValidator, UrlValidator urlValidator) {
        this.idValidator = idValidator;
        this.urlValidator = urlValidator;
    }

    public ArticleValidator articleValidator() {
        return new ArticleValidatorImpl(idValidator, urlValidator);
    }
}
