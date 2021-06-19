package nl.viewsource.articleapi.usecase.exception;

public class ArticleValidationException extends Exception {

    public ArticleValidationException(String message) {
        super(message);
    }

    public ArticleValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
