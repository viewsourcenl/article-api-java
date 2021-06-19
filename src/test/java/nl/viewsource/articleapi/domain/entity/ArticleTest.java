package nl.viewsource.articleapi.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleTest {
    Article.Builder builder;

    String title;
    String description;
    String link;
    String image;
    Date date;

    @BeforeEach
    void beforeEach() {
        builder = Article.builder()
                .title(title = "Something Something")
                .description(description = "You won't believe what happened.")
                .link(link = "https://www.example.com/article/123")
                .image(image = "https://www.example.com/article/pic.png")
                .date(date = new Date())
        ;
    }

    @Test
    @DisplayName("builds without error")
    void happy() {
        Article article = assertDoesNotThrow(() ->
                builder.build()
        );

        assertEquals(title, article.title);
        assertEquals(description, article.description);
        assertEquals(link, article.link);
        assertEquals(image, article.image);
        assertEquals(date, article.date);
    }

    @Test
    @DisplayName("must have a title")
    void requiresTitle() {
        builder.title(null);

        Exception ex = assertThrows(
                ValidationException.class,
                () -> builder.build()
        );
        assertEquals("Article must have a title", ex.getMessage());
    }

    @Test
    @DisplayName("must have some text in the title")
    void titleMinimalLength() {
        builder.title("ap");
        Exception ex = assertThrows(
                ValidationException.class,
                () -> builder.build()
        );
        assertEquals("Article title should be a minimal of 3 characters", ex.getMessage());
    }

    @Test
    @DisplayName("must have a description")
    void requiresDescription() {
        builder.description(null);

        Exception ex = assertThrows(
                ValidationException.class,
                () -> builder.build()
        );
        assertEquals("Article must have a description", ex.getMessage());
    }

    @Test
    @DisplayName("must have a date")
    void requiresDate() {
        builder.date(null);

        Exception ex = assertThrows(
                ValidationException.class,
                () -> builder.build()
        );
        assertEquals("Article must have a date", ex.getMessage());
    }

    @Test
    @DisplayName("must have a link")
    void requiresLink() {
        builder.link(null);

        Exception ex = assertThrows(
                ValidationException.class,
                () -> builder.build()
        );
        assertEquals("Article must have a link", ex.getMessage());
    }

    @Test
    @DisplayName("should have a valid url for a link")
    void validLinkUrl() {
        builder.link("some/link");

        Exception ex = assertThrows(
                ValidationException.class,
                () -> builder.build()
        );
        assertEquals("Article must have a valid link", ex.getMessage());
    }

    @Test
    @DisplayName("must have an image")
    void requiresImage() {
        builder.image(null);

        Exception ex = assertThrows(
                ValidationException.class,
                () -> builder.build()
        );
        assertEquals("Article must have an image", ex.getMessage());
    }

    @Test
    @DisplayName("should have a valid url for an image")
    void validImageUrl() {
        builder.image("some/image");

        Exception ex = assertThrows(
                ValidationException.class,
                () -> builder.build()
        );
        assertEquals("Article must have a valid image url", ex.getMessage());
    }

    @Disabled
    @Test
    @DisplayName("can have an id")
    void canHaveId() throws ValidationException {
        builder.id("SomeOtherId");
        Article article = builder.build();
        assertEquals("SomeOtherId", article.id);
    }

    @Test
    @DisplayName("can create an id ")
    void createId() throws ValidationException {
        builder.id(null);
        Article article = builder.build();
        assertNotNull(article.id);
    }


    @Test
    @DisplayName("accepts tags as list")
    void canHaveTagsAsList() throws ValidationException {
        builder.tags(List.of("Linux", "Java"));
        Article article = builder.build();
        assertEquals(List.of("Linux", "Java"), article.tags);
    }

    @Test
    @DisplayName("accepts tags as list")
    void canHaveTagsAsVarArgs() throws ValidationException {
        builder.tags("Linux", "Java");
        Article article = builder.build();
        assertEquals(List.of("Linux", "Java"), article.tags);
    }
}
