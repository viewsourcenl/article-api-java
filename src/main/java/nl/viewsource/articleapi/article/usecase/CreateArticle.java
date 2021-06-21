package nl.viewsource.articleapi.article.usecase;

import nl.viewsource.articleapi.article.entity.Article;
import nl.viewsource.articleapi.article.entity.ArticleValidationException;
import nl.viewsource.articleapi.article.entity.ArticleValidator;
import nl.viewsource.articleapi.article.usecase.port.ArticleRepository;
import nl.viewsource.articleapi.article.usecase.port.IdGenerator;

public final class CreateArticle {
    private final ArticleRepository repository;
    private final IdGenerator idGenerator;
    private final ArticleValidator articleValidator;

    public CreateArticle(final ArticleRepository repository, final IdGenerator idGenerator, final ArticleValidator articleValidator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
        this.articleValidator = articleValidator;
    }

    public Article create(final Article article) throws ArticleValidationException {
        var articleToSave = Article.builder(article)
                .id(idGenerator.generate())
                .build();

        articleValidator.validate(articleToSave);

        return repository.create(articleToSave);
    }
}
