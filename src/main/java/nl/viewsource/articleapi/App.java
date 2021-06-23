package nl.viewsource.articleapi;

import nl.viewsource.articleapi.adapter.id.JugIdGenerator;
import nl.viewsource.articleapi.adapter.id.UuidGenerator;
import nl.viewsource.articleapi.adapter.repository.MapBasedArticleRepository;
import nl.viewsource.articleapi.adapter.url.URLValidator;
import nl.viewsource.articleapi.article.entity.ArticleValidatorFactory;
import nl.viewsource.articleapi.article.usecase.ArticleUsecaseFactory;
import nl.viewsource.articleapi.article.usecase.port.ArticleRepository;
import nl.viewsource.articleapi.controller.ArticleController;
import nl.viewsource.articleapi.resources.ArticleResource;
import nl.viewsource.articleapi.resources.ArticlesResource;
import nl.viewsource.articleapi.resources.ArticleTagsResource;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class App extends Application {


    @Override
    public Restlet createInboundRoot() {
        var config = new AppConfigMemory();
        Router router = new Router(getContext());
        router.getContext().getAttributes().put("articleController", config.getArticleController());

        router.attach("/articles", ArticlesResource.class);
        router.attach("/articles/{articleId}", ArticleResource.class);
        router.attach("/articles/{articleId}/tags", ArticleTagsResource.class);

        return router;
    }

    public static void main(String[] args) throws Exception {
        var component = new Component();
        component.getServers().add(Protocol.HTTP, 8008);
        var host = component.getDefaultHost();

        host.attach("/api", new App());

        component.start();
    }
}
