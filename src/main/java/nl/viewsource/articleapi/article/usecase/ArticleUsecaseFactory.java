package nl.viewsource.articleapi.article.usecase;

import nl.viewsource.articleapi.article.entity.ArticleValidator;
import nl.viewsource.articleapi.article.usecase.port.ArticleRepository;
import nl.viewsource.articleapi.article.usecase.port.IdGenerator;

import java.util.Objects;

public record ArticleUsecaseFactory(ArticleRepository repository,
                                    ArticleValidator articleValidator,
                                    IdGenerator idGenerator) {

    public ArticleUsecaseFactory {
         Objects.requireNonNull(repository);
         Objects.requireNonNull(articleValidator);
         Objects.requireNonNull(idGenerator);
    }

    private static ArticleUsecaseFactory instance;

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
