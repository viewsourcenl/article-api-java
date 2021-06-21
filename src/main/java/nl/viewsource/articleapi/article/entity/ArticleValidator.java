package nl.viewsource.articleapi.article.entity;

public interface ArticleValidator {
    void validate(Article article) throws ArticleValidationException;
}
