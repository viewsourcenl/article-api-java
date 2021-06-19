package nl.viewsource.articleapi.usecase;

import nl.viewsource.articleapi.domain.entity.Article;
import nl.viewsource.articleapi.usecase.exception.ArticleValidationException;
import nl.viewsource.articleapi.usecase.port.ArticleRepository;
import nl.viewsource.articleapi.usecase.port.ArticleValidator;
import nl.viewsource.articleapi.usecase.port.IdGenerator;



public class CreateArticle {
    private final ArticleRepository repository;
    private final IdGenerator idGenerator;
    private final ArticleValidator validator;

    public CreateArticle(final ArticleRepository repository, final IdGenerator idGenerator, final ArticleValidator validator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
        this.validator = validator;
    }

    public Article create(final Article article) throws ArticleValidationException {
        var articleToSave = Article.builder()
                .id(idGenerator.generate())
                .title(article.title)
                .description(article.description)
                .tags(article.tags)
                .link(article.link)
                .image(article.image)
                .date(article.date)
                .build();

        validator.validate(articleToSave);

        return repository.create(articleToSave);
    }

}
