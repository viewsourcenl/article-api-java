package nl.viewsource.articleapi;

import nl.viewsource.articleapi.article.usecase.ArticleUsecaseFactory;
import nl.viewsource.articleapi.controller.ArticleController;

public interface AppConfig {
    ArticleUsecaseFactory getArticleUsecaseFactory() ;
    ArticleController getArticleController();
}
