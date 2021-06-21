package nl.viewsource.articleapi.article.entity;

public class ArticleValidationException extends Exception {

    public ArticleValidationException(String message) {
        super(message);
    }

    public ArticleValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
