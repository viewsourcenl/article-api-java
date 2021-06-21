package nl.viewsource.articleapi.resources;

import nl.viewsource.articleapi.controller.ArticleController;
import nl.viewsource.articleapi.controller.model.ArticleWeb;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class ArticleResource extends ServerResource {
    private ArticleController controller;
    private String articleId;

    @Override
    protected void doInit() throws ResourceException {
        this.articleId = getAttribute("articleId");
        this.controller = (ArticleController)getContext().getAttributes().get("articleController");
    }


    @Get("json")
    public ArticleWeb getArticle() {
        return controller.getArticle(this.articleId).get();
    }
}
