package nl.viewsource.articleapi.usecase.port;

import nl.viewsource.articleapi.domain.entity.Article;
import nl.viewsource.articleapi.usecase.exception.ArticleValidationException;

public interface ArticleValidator {

    public void validate(Article article) throws ArticleValidationException;
}
