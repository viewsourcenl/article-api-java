package nl.viewsource.articleapi.article.entity;

import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Article article = Assertions.assertDoesNotThrow(() ->
                builder.build()
        );

        assertEquals(title, article.title);
        assertEquals(description, article.description);
        assertEquals(link, article.link);
        assertEquals(image, article.image);
        assertEquals(date, article.date);
    }

    @Test
    @DisplayName("can have an id")
    void canHaveId() {
        builder.id("SomeOtherId");
        Article article = builder.build();
        assertEquals("SomeOtherId", article.id);
    }

    @Test
    @DisplayName("accepts tags as list")
    void canHaveTagsAsList() {
        builder.tags(List.of("Linux", "Java"));
        Article article = builder.build();
        assertEquals(List.of("Linux", "Java"), article.tags);
    }

    @Test
    @DisplayName("accepts tags multiple strings")
    void canHaveTagsAsVarArgs() {
        builder.tags("Linux", "Java");
        Article article = builder.build();
        assertEquals(List.of("Linux", "Java"), article.tags);
    }
}
