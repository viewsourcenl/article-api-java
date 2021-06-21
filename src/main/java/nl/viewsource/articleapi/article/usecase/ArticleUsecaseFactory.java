package nl.viewsource.articleapi.article.usecase;

import nl.viewsource.articleapi.article.entity.ArticleValidator;
import nl.viewsource.articleapi.article.usecase.port.ArticleRepository;
import nl.viewsource.articleapi.article.usecase.port.IdGenerator;

public class ArticleUsecaseFactory {

    private static ArticleUsecaseFactory instance;
    private final ArticleRepository repository;
    private final ArticleValidator articleValidator;
    private final IdGenerator idGenerator;

    public ArticleUsecaseFactory(ArticleRepository repository, ArticleValidator articleValidator, IdGenerator idGenerator) {
        this.repository = repository;
        this.articleValidator = articleValidator;
        this.idGenerator = idGenerator;
    }

    public CreateArticle createArticle() {
            return new CreateArticle(this.repository, this.idGenerator, this.articleValidator);
    }

    public FindArticle findArticle() {
        return new FindArticle(this.repository);
    }

    public UpdateTags updateTags() {
        return new UpdateTags(this.repository);
    }
}
