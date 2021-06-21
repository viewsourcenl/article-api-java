package nl.viewsource.articleapi.resources;

import org.restlet.resource.Status;

@Status(value = 400, serialize = true)
public class ResourceValidationException extends RuntimeException {
    public ResourceValidationException(Exception e) {
        super(e.getMessage(), e);
    }
}
