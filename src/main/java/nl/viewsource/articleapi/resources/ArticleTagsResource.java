package nl.viewsource.articleapi.resources;

import nl.viewsource.articleapi.controller.ArticleController;
import nl.viewsource.articleapi.controller.model.ArticleWeb;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.List;
import java.util.Optional;

public class ArticleTagsResource extends ServerResource {
    private ArticleController controller;
    private String articleId;

    @Override
    protected void doInit() throws ResourceException {
        this.articleId = getAttribute("articleId");
        this.controller = (ArticleController)getContext().getAttributes().get("articleController");
    }

    @Put("json")
    public ArticleWeb putTags(List<String> tags) {
        return controller.addTags(this.articleId, tags).get();
    }

    @Delete("json")
    public ArticleWeb deleteTags(List<String> tags) {
        return controller.removeTags(this.articleId, tags).get();
    }
}
