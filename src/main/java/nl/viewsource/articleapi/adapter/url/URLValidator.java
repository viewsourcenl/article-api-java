package nl.viewsource.articleapi.adapter.url;

import nl.viewsource.articleapi.article.entity.port.UrlValidator;

import java.net.MalformedURLException;
import java.net.URL;

public class URLValidator implements UrlValidator {
    @Override
    public boolean isValid(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
