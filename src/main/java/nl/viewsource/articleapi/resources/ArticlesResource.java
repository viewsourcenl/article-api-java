package nl.viewsource.articleapi.resources;

import nl.viewsource.articleapi.article.entity.ArticleValidationException;
import nl.viewsource.articleapi.controller.ArticleController;
import nl.viewsource.articleapi.controller.model.ArticleWeb;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.List;

public class ArticlesResource extends ServerResource {
    private ArticleController controller;

    @Override
    protected void doInit() throws ResourceException {
        this.controller = (ArticleController)getContext().getAttributes().get("articleController");
    }

    @Get("json")
    public List<ArticleWeb> getArticles() {
       return controller.getAllArticles();
    }

    @Post("json")
    public ArticleWeb postArticle(ArticleWeb article) {
        try {
            return this.controller.createArticle(article);
        } catch (ArticleValidationException e) {
            throw new ResourceValidationException(e);
        }
    }
}
