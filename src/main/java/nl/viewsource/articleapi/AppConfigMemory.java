package nl.viewsource.articleapi;

import nl.viewsource.articleapi.adapter.id.JugIdGenerator;
import nl.viewsource.articleapi.adapter.id.UuidGenerator;
import nl.viewsource.articleapi.adapter.repository.MapBasedArticleRepository;
import nl.viewsource.articleapi.adapter.url.URLValidator;
import nl.viewsource.articleapi.article.entity.ArticleValidatorFactory;
import nl.viewsource.articleapi.article.usecase.ArticleUsecaseFactory;
import nl.viewsource.articleapi.article.usecase.port.ArticleRepository;
import nl.viewsource.articleapi.controller.ArticleController;

public class AppConfigMemory implements AppConfig {
    private final ArticleRepository repository = new MapBasedArticleRepository();
    private final UuidGenerator uuidGenerator = new UuidGenerator();
    private final JugIdGenerator jugIdGenerator = new JugIdGenerator();
    private final URLValidator urlValidator = new URLValidator();
    private final ArticleValidatorFactory articleValidatorFactory = new ArticleValidatorFactory(uuidGenerator, urlValidator);
    private final ArticleUsecaseFactory articleUsecaseFactory = new ArticleUsecaseFactory(
            repository,
            articleValidatorFactory.articleValidator(),
            jugIdGenerator
    );

    private final ArticleController articleController = new ArticleController(
            articleUsecaseFactory.createArticle(),
            articleUsecaseFactory.findArticle(),
            articleUsecaseFactory.updateTags()
    );

    public ArticleUsecaseFactory getArticleUsecaseFactory() {
        return articleUsecaseFactory;
    }

    public ArticleController getArticleController() {
        return this.articleController;
    }

}
