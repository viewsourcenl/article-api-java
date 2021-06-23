package nl.viewsource.articleapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class AppConfigTest {
    AppConfig config;
    @BeforeEach
    void beforeEach() {
        config = new AppConfigMemory();
    }

    @Test
    void isDefined_ArticleUsecaseFactory() {
        assertNotNull(config.getArticleUsecaseFactory());
    }

    @Test
    void isDefined_ArticleController() {
        assertNotNull(config.getArticleController());
    }

}