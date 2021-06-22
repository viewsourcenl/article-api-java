package nl.viewsource.articleapi.article.entity;

import nl.viewsource.articleapi.article.entity.port.IdValidator;
import nl.viewsource.articleapi.article.entity.port.UrlValidator;
import org.junit.jupiter.api.*;

import java.util.Date;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleValidatorTest {
    Article.Builder builder;
    ArticleValidator articleValidator;
    IdValidator idValidator;
    UrlValidator urlValidator;

    String title;
    String description;
    String link;
    String image;
    Date date;

    @BeforeEach
    void beforeEach() {
        idValidator = mock(IdValidator.class,   invocationOnMock -> true);
        urlValidator = mock(UrlValidator.class, invocationOnMock -> true);

        articleValidator = new ArticleValidatorImpl(idValidator, urlValidator);

        builder = Article.builder()
                .title(title = "Something Something")
                .description(description = "You won't believe what happened.")
                .link(link = "https://www.example.com/article/123")
                .image(image = "https://www.example.com/article/pic.png")
                .date(date = new Date())
        ;
    }

    @Test
    @DisplayName("must have a valid id")
    void requiresId() {
        var article = builder.id("blabla").build();
        when(idValidator.isValid(article.id)).thenReturn(false);

        Exception ex = assertThrows(
                ArticleValidationException.class,
                () -> articleValidator.validate(article)
        );
        assertEquals("Article must have a valid id", ex.getMessage());
    }

    @Test
    @DisplayName("must have a title")
    void requiresTitle() {
        var article = builder.title(null).build();

        Exception ex = assertThrows(
                ArticleValidationException.class,
                () -> articleValidator.validate(article)
        );
        assertEquals("Article must have a title", ex.getMessage());
    }

    @Test
    @DisplayName("must have some text in the title")
    void titleMinimalLength() {
        var article = builder.title("ap").build();
        Exception ex = assertThrows(
                ArticleValidationException.class,
                () -> articleValidator.validate(article)
        );
        assertEquals("Article title should be a minimal of 3 characters", ex.getMessage());
    }

    @Test
    @DisplayName("must have a description")
    void requiresDescription() {
        var article = builder.description(null).build();

        Exception ex = assertThrows(
                ArticleValidationException.class,
                () -> articleValidator.validate(article)
        );
        assertEquals("Article must have a description", ex.getMessage());
    }

    @Test
    @DisplayName("must have a date")
    void requiresDate() {
        var article = builder.date(null).build();

        Exception ex = assertThrows(
                ArticleValidationException.class,
                () -> articleValidator.validate(article)
        );
        assertEquals("Article must have a date", ex.getMessage());
    }

    @Test
    @DisplayName("must have a link")
    void requiresLink() {
        var article = builder.link(null).build();

        Exception ex = assertThrows(
                ArticleValidationException.class,
                () -> articleValidator.validate(article)
        );
        assertEquals("Article must have a link", ex.getMessage());
    }

    @Test
    @DisplayName("should have a valid url for a link")
    void validLinkUrl() {
        var article = builder.link("some/link").build();
        when(urlValidator.isValid(article.link)).thenReturn(false);

        Exception ex = assertThrows(
                ArticleValidationException.class,
                () -> articleValidator.validate(article)
        );
        assertEquals("Article must have a valid link", ex.getMessage());
    }

    @Test
    @DisplayName("must have an image")
    void requiresImage() {
        var article = builder.image(null).build();

        Exception ex = assertThrows(
                ArticleValidationException.class,
                () -> articleValidator.validate(article)
        );
        assertEquals("Article must have an image", ex.getMessage());
    }

    @Test
    @DisplayName("should have a valid url for an image")
    void validImageUrl() {
        var article = builder.image("some/image").build();

        when(urlValidator.isValid(article.image)).thenReturn(false);

        Exception ex = assertThrows(
                ArticleValidationException.class,
                () -> articleValidator.validate(article)
        );
        assertEquals("Article must have a valid image url", ex.getMessage());
    }
}
